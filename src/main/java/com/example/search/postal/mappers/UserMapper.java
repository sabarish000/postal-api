package com.example.search.postal.mappers;

import com.example.search.postal.dtos.UserRequestDTO;
import com.example.search.postal.dtos.UserResponseDTO;
import com.example.search.postal.models.Role;
import com.example.search.postal.models.User;
import com.example.search.postal.security.PasswordUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(PasswordUtils.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole() != null ? userRequestDTO.getRole() : Role.USER);
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getRole());
        return responseDTO;
    }

    public static List<UserResponseDTO> toResponseDTOs(List<User> users) {
        return users.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
