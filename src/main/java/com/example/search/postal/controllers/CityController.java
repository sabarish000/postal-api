package com.example.search.postal.controllers;

import com.example.search.postal.dtos.CityResponseDTO;
import com.example.search.postal.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@Tag(name = "City Controller", description = "Handles city-related operations")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Operation(
            summary = "Search Cities",
            description = "Searches for cities matching the provided search text.",
            tags = {"City"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of matching cities.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CityResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failure for the search text.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/search")
    public ResponseEntity<List<CityResponseDTO>> getCities(@NotBlank(message = "Search text is required") @RequestParam(name="search_text") String searchText) {
        List<CityResponseDTO> results = cityService.searchCities(searchText);
        return ResponseEntity.ok(results);
    }
}
