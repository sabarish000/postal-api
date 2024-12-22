package com.example.search.postal.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@Schema(description = "Represents a city.")
public class CityResponseDTO {
    @Schema(description = "Unique city code.", example = "BE001-BE-GE")
    private String code;
    @Schema(description = "Name of the city.", example = "Berlin")
    private String name;
    @Schema(description = "Region of the city.", example = "Berlin Capital Region")
    private String region;
    @Schema(description = "District of the city.", example = "Mitte")
    private String district;
    @Schema(description = "State where the city is located.", example = "Berlin")
    private String state;
    @Schema(description = "Country where the city is located.", example = "Germany")
    private String country;
}
