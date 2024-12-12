package com.example.search.postal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
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
    @NotBlank(message = "City is mandatory")
    private String city;
    private String region;
    private String district;
    @NotBlank(message = "State is mandatory")
    private String state;
    @NotBlank(message = "Country is mandatory")
    private String country;
}
