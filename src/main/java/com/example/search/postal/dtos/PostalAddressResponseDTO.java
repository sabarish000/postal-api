package com.example.search.postal.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
public class PostalAddressResponseDTO {
    private String postalCode;
    private String street;
    private String number;
    private String city;
    private String region;
    private String district;
    private String state;
    private String country;
}
