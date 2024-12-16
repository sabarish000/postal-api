package com.example.search.postal.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class CityResponseDTO {
    private String code;
    private String name;
    private String region;
    private String district;
    private String state;
    private String country;
}
