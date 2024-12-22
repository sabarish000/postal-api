package com.example.search.postal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@Schema(description = "Represents a postal address response.")
public class PostalAddressResponseDTO {
    @Schema(description = "Unique identifier for the postal address.", example = "1")
    private Long id;
    @Schema(description = "Postal code of the address.", example = "12345")
    private String postalCode;
    @Schema(description = "Street name of the address.", example = "Main Street")
    private String street;
    @Schema(description = "Number of the building.", example = "123")
    private String number;
    @Schema(description = "City name for the address.", example = "Berlin")
    private String city;
    @Schema(description = "Region name for the address.", example = "Berlin Capital region")
    private String region;
    @Schema(description = "District name for the address.", example = "Mitte")
    private String district;
    @Schema(description = "State name for the address.", example = "Berlin State")
    private String state;
    @Schema(description = "Country name for the address.", example = "Germany")
    private String country;
}
