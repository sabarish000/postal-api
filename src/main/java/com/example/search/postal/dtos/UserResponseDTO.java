package com.example.search.postal.dtos;

import com.example.search.postal.models.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Represents a user's response data.")
public class UserResponseDTO {
        @Schema(description = "Unique identifier for the user.", example = "1")
        private Long id;
        @Schema(description = "Name of the user.", example = "John Cena")
        private String username;
        @Schema(description = "Email of the user.", example = "john.cena@example.com")
        private Role role;

}
