package com.example.search.postal.controllers;

import com.example.search.postal.dtos.AuthResponseDTO;
import com.example.search.postal.dtos.LoginRequestDTO;
import com.example.search.postal.models.User;
import com.example.search.postal.security.JwtTokenUtil;
import com.example.search.postal.services.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for user authentication and authorization")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final IAuthService authService;
    private final JwtTokenUtil jwtTokenUtil;
    public AuthController(IAuthService authService, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Operation(
            summary = "User Login",
            description = "Authenticates the user, generates a JWT token, and returns user details along with the token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login successful, JWT token and user details returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid username or password",
                            content = @Content
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        User loggedInUser = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        String token = jwtTokenUtil.generateToken(loggedInUser.getUsername());
        logger.debug(loggedInUser.getUsername() + " is logged in");
        return ResponseEntity.ok(new AuthResponseDTO(loggedInUser.getUsername(), token, loggedInUser.getRole().name()));
    }
}
