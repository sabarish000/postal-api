package com.example.search.postal.controllers;

import com.example.search.postal.dtos.UserRequestDTO;
import com.example.search.postal.dtos.UserResponseDTO;
import com.example.search.postal.models.Role;
import com.example.search.postal.security.TestSecurityConfig;
import com.example.search.postal.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(UserControllerTest.class)
@Import(TestSecurityConfig.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IUserService userService;

    @InjectMocks
    private UserController userController;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("testuser");
        userRequestDTO.setPassword("afhkh");
        userRequestDTO.setRole(Role.USER);

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("testuser");
        userResponseDTO.setRole(Role.USER);
    }

    @Test
    @WithMockUser
    public void testCreateUser_Success() throws Exception {
        when(userService.createUser(any(UserRequestDTO.class)))
                .thenReturn(userResponseDTO);

        String requestBody = objectMapper.writeValueAsString(userRequestDTO);
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void testCreateUser_ValidationFailure() throws Exception {
        UserRequestDTO invalidRequest = new UserRequestDTO(); // Empty request
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(invalidRequest);
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void testGetAllUsers_Success() throws Exception {
        List<UserResponseDTO> users = List.of(userResponseDTO);
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("testuser"));
    }

    @Test
    @WithMockUser
    public void testGetUserById_Success() throws Exception {
        when(userService.getUserById(anyLong()))
                .thenReturn(userResponseDTO);
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    @WithMockUser
    public void testUpdateUser_Success() throws Exception {
        when(userService.updateUser(anyLong(), any(UserRequestDTO.class))).thenReturn(userResponseDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(userRequestDTO);
        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    @WithMockUser
    public void testUpdateUser_ValidationFailure() throws Exception {
        UserRequestDTO invalidRequest = new UserRequestDTO(); // Empty request
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(invalidRequest);
        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void testDeleteUser_Success() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void testGetUserById_NotFound() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(null);
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testUpdateUser_NotFound() throws Exception {
        when(userService.updateUser(anyLong(), any(UserRequestDTO.class))).thenReturn(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(userRequestDTO);
        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testDeleteUser_NotFound() throws Exception {
        Mockito.doThrow(new RuntimeException("User not found")).when(userService).deleteUser(anyLong());
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNotFound());
    }
}
