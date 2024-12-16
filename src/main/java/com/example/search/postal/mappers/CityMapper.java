package com.example.search.postal.mappers;

import com.example.search.postal.dtos.CityResponseDTO;
import com.example.search.postal.models.City;

import java.util.List;
import java.util.stream.Collectors;

public class CityMapper {

    public static CityResponseDTO toResponseDTO(City city) {
        return CityResponseDTO.builder()
                .code(city.getCode())
                .name(city.getName())
                .region(city.getRegion())
                .district(city.getDistrict())
                .state(city.getState())
                .country(city.getCountry())
                .build();
    }

    public static List<CityResponseDTO> toResponseDTOs(List<City> cities) {
        return cities.stream()
                .map(CityMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
