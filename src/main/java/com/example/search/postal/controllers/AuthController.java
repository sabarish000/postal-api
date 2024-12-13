package com.example.search.postal.controllers;

import com.example.search.postal.dtos.AuthResponseDTO;
import com.example.search.postal.dtos.LoginRequestDTO;
import com.example.search.postal.security.JwtTokenUtil;
import com.example.search.postal.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;
    public AuthController(AuthService authService, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        String token = jwtTokenUtil.generateToken(loginRequest.getUsername());
        System.out.println("JWT token" + token);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
