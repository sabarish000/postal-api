# Provider configuration
provider "aws" {
  region = "us-east-1" # Change to your desired region
}

# S3 bucket to store the Lambda deployment package
resource "aws_s3_bucket" "lambda_bucket" {
  bucket = "spring-boot-lambda-bucket"
  acl    = "private"
}

# Upload the JAR file (Spring Boot application) to S3
resource "aws_s3_object" "lambda_jar" {
  bucket = aws_s3_bucket.lambda_bucket.id
  key    = "spring-boot-app.jar" # Change based on your JAR file name
  source = "./build/libs/spring-boot-app.jar" # Path to your JAR file
}

# IAM role for Lambda execution
resource "aws_iam_role" "lambda_exec_role" {
  name = "lambda_execution_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action    = "sts:AssumeRole",
        Effect    = "Allow",
        Principal = { Service = "lambda.amazonaws.com" }
      }
    ]
  })
}

# Attach basic execution policy to the IAM role
resource "aws_iam_role_policy_attachment" "lambda_basic_execution" {
  role       = aws_iam_role.lambda_exec_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

# Create the Lambda function
resource "aws_lambda_function" "spring_boot_lambda" {
  function_name = "spring-boot-lambda"
  runtime       = "java11"
  role          = aws_iam_role.lambda_exec_role.arn
  handler       = "org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest"
  
  s3_bucket     = aws_s3_bucket.lambda_bucket.id
  s3_key        = aws_s3_object.lambda_jar.key

  memory_size   = 512
  timeout       = 30

  environment {
    variables = {
      SPRING_PROFILES_ACTIVE = "lambda"
    }
  }
}

# API Gateway to expose Lambda as REST endpoints
resource "aws_apigatewayv2_api" "http_api" {
  name          = "spring-boot-api"
  protocol_type = "HTTP"
}

# API Gateway integration with Lambda
resource "aws_apigatewayv2_integration" "lambda_integration" {
  api_id             = aws_apigatewayv2_api.http_api.id
  integration_type   = "AWS_PROXY"
  integration_uri    = aws_lambda_function.spring_boot_lambda.invoke_arn
  payload_format_version = "2.0"
}

# API Gateway route for all paths
resource "aws_apigatewayv2_route" "default_route" {
  api_id    = aws_apigatewayv2_api.http_api.id
  route_key = "$default"

  target    = aws_apigatewayv2_integration.lambda_integration.id
}

# Deploy API Gateway stage
resource "aws_apigatewayv2_stage" "api_stage" {
  api_id      = aws_apigatewayv2_api.http_api.id
  name        = "$default"
  auto_deploy = true
}

# Permission for API Gateway to invoke the Lambda function
resource "aws_lambda_permission" "apigw_invoke_permission" {
  statement_id   = "AllowAPIGatewayInvoke"
  action         = "lambda:InvokeFunction"
  function_name  = aws_lambda_function.spring_boot_lambda.function_name
  principal      = "apigateway.amazonaws.com"
  
  source_arn     = "${aws_apigatewayv2_api.http_api.execution_arn}/*/*"
}
