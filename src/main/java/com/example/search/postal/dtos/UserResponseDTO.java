package com.example.search.postal.dtos;

import com.example.search.postal.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
        private Long id;
        private String username;
        private Role role;

}
