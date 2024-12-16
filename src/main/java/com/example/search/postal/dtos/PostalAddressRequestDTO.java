package com.example.search.postal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostalAddressRequestDTO {
    @NotBlank(message = "Postal code is mandatory")
    private String postalCode;
    @NotBlank(message = "Street is mandatory")
    private String street;
    private String number;
    @NotBlank(message = "City code is mandatory")
    private String cityCode;
}
