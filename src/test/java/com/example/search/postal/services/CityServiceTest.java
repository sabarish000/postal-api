package com.example.search.postal.services;

import com.example.search.postal.dtos.CityResponseDTO;
import com.example.search.postal.models.City;
import com.example.search.postal.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City city1;
    private City city2;

    @BeforeEach
    public void setUp() {
        city1 = new City();
        city1.setName("Berlin");
        city1.setDistrict("Mitte");
        city1.setState("Berlin");

        city2 = new City();
        city2.setName("Hamburg");
        city2.setDistrict("Nord");
        city2.setState("Hamburg");
    }

    @Test
    public void testSearchCities() {
        String searchText = "Berlin";
        when(cityRepository.findByNameContainingIgnoreCaseOrDistrictContainingIgnoreCaseOrStateContainingIgnoreCase(
                searchText, searchText, searchText)).thenReturn(Arrays.asList(city1, city2));

        List<CityResponseDTO> result = cityService.searchCities(searchText);

        assertEquals(2, result.size());
        assertEquals("Berlin", result.get(0).getName());
        assertEquals("Hamburg", result.get(1).getName());
    }
}
