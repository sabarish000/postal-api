package com.example.search.postal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    private static final Key KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode("2zYjVlZmk5yMEcQRtyFQd2DoTYtRz2Ez8Bsb6IlMiFU="));

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
        return getClaims(token).getSubject();
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
