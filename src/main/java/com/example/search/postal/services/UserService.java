package com.example.search.postal.services;

import com.example.search.postal.dtos.UserRequestDTO;
import com.example.search.postal.dtos.UserResponseDTO;
import com.example.search.postal.exceptions.ResourceNotFoundException;
import com.example.search.postal.mappers.UserMapper;
import com.example.search.postal.models.User;
import com.example.search.postal.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }

    // Get user by ID
    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toResponseDTO(user);
    }

    // Get all users
    @Override
    public List<UserResponseDTO> getAllUsers() {
        return UserMapper.toResponseDTOs(userRepository.findAll());
    }

    // Update user by ID
    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (userRequestDTO.getUsername() != null) {
            user.setUsername(userRequestDTO.getUsername());
        }
        if (userRequestDTO.getPassword() != null) {
            user.setPassword(userRequestDTO.getPassword());
        }
        if (userRequestDTO.getRole() != null) {
            user.setRole(userRequestDTO.getRole());
        }

        User updatedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(updatedUser);
    }

    // Delete user by ID
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    /**
     * Retrieves the authorities (roles) assigned to the given username.
     *
     * @param username The username of the user.
     * @return A list of granted authorities associated with the user.
     * @throws RuntimeException if the user is not found.
     */
    @Override
    public List<GrantedAuthority> getAuthorities(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        // Convert user roles to Spring Security's GrantedAuthority
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }
}
