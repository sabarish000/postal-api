package com.example.search.postal.services;

import com.example.search.postal.dtos.CityResponseDTO;

import java.util.List;

public interface ICityService {
    List<CityResponseDTO> searchCities(String searchText);
}
