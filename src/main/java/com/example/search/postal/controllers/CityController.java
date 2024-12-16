package com.example.search.postal.controllers;

import com.example.search.postal.dtos.CityResponseDTO;
import com.example.search.postal.services.CityService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<CityResponseDTO>> getCities(@NotBlank(message = "Search text is required") @RequestParam(name="search_text") String searchText) {
        List<CityResponseDTO> results = cityService.searchCities(searchText);
        return ResponseEntity.ok(results);
    }
}
