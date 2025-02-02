package com.example.search.postal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private final long EXPIRATION_TIME;// 1 hour
    // In real world, uses a Secrets Manager (AWS Secrets Manager, HashiCorp Vault, Azure Key Vault)
    private final Key KEY;

    public JwtTokenUtil(@Value("${jwt.secret}") String secret,
                        @Value("${jwt.expiration}") Long expiationInMillis) {
        KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        EXPIRATION_TIME = expiationInMillis;
    }

    // Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return getClaims(token)
                .getSubject();
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Extract claims
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
