package com.example.search.postal.controllers;

import com.example.search.postal.dtos.AuthResponseDTO;
import com.example.search.postal.dtos.LoginRequestDTO;
import com.example.search.postal.models.Role;
import com.example.search.postal.models.User;
import com.example.search.postal.security.JwtTokenUtil;
import com.example.search.postal.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private AuthService authService;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @InjectMocks
    private AuthController authController;
    private LoginRequestDTO loginRequestDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("testuser");
        loginRequestDTO.setPassword("testpassword");
        user = new User();
        user.setUsername("testuser");
        user.setRole(Role.USER);
    }

    @Test
    public void testLogin_Success() {
        when(authService.authenticate(anyString(), anyString())).thenReturn(user);
        when(jwtTokenUtil.generateToken(anyString())).thenReturn("mockJwtToken");
        ResponseEntity<AuthResponseDTO> response = authController.login(loginRequestDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mockJwtToken", response.getBody().getToken());
    }

    @Test
    public void testLogin_InvalidCredentials() {
        when(authService.authenticate(anyString(), anyString())).thenThrow(new RuntimeException("Invalid credentials"));
        try {
            authController.login(loginRequestDTO);
        } catch (RuntimeException e) {
            assertEquals("Invalid credentials", e.getMessage());
        }
    }
}
