package com.example.search.postal.controllers;

import com.example.search.postal.dtos.CityResponseDTO;
import com.example.search.postal.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    private List<CityResponseDTO> cityResponseDTOList;

    @BeforeEach
    public void setUp() {
        CityResponseDTO cityResponseDTO1 = CityResponseDTO.builder()
                .name("Berlin")
                .district("Mitte")
                .state("Berlin")
                .build();

        CityResponseDTO cityResponseDTO2 = CityResponseDTO.builder()
                .name("Hamburg")
                .district("Nord")
                .state("Nord")
                .build();

        cityResponseDTOList = Arrays.asList(cityResponseDTO1, cityResponseDTO2);
    }

    @Test
    public void testGetCities_Success() {
        when(cityService.searchCities(anyString())).thenReturn(cityResponseDTOList);

        ResponseEntity<List<CityResponseDTO>> response = cityController.getCities("Berlin");

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(cityResponseDTOList, response.getBody());
    }

    @Test
    public void testGetCities_EmptySearchText() {
        when(cityService.searchCities(anyString())).thenReturn(Arrays.asList());

        ResponseEntity<List<CityResponseDTO>> response = cityController.getCities("");

        assertTrue( response.getStatusCode().is2xxSuccessful());
        assertTrue( response.getBody().isEmpty());
    }
}
