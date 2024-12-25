package com.example.search.postal.services;

import com.example.search.postal.dtos.UserRequestDTO;
import com.example.search.postal.dtos.UserResponseDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IUserService {
    // Create a new user
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    // Get user by ID
    UserResponseDTO getUserById(Long id);

    // Get all users
    List<UserResponseDTO> getAllUsers();

    // Update user by ID
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    // Delete user by ID
    void deleteUser(Long id);

    List<GrantedAuthority> getAuthorities(String username);
}
