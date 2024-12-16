package com.example.search.postal.services;

import com.example.search.postal.dtos.CityResponseDTO;
import com.example.search.postal.mappers.CityMapper;
import com.example.search.postal.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityResponseDTO> searchCities(String searchText) {
        return CityMapper.toResponseDTOs(cityRepository.findByNameContainingIgnoreCaseOrDistrictContainingIgnoreCaseOrStateContainingIgnoreCase(searchText, searchText, searchText));
    }
}
