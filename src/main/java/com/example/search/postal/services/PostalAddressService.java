package com.example.search.postal.services;

import com.example.search.postal.dtos.PostalAddressRequestDTO;
import com.example.search.postal.dtos.PostalAddressResponseDTO;
import com.example.search.postal.exceptions.ResourceNotFoundException;
import com.example.search.postal.mappers.PostalAddressMapper;
import com.example.search.postal.models.City;
import com.example.search.postal.models.PostalAddress;
import com.example.search.postal.repositories.CityRepository;
import com.example.search.postal.repositories.PostalAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostalAddressService {
    private final PostalAddressRepository postalAddressRepository;
    private final CityRepository cityRepository;

    public PostalAddressService(PostalAddressRepository postalAddressRepository, CityRepository cityRepository) {
        this.postalAddressRepository = postalAddressRepository;
        this.cityRepository = cityRepository;
    }

    public List<PostalAddressResponseDTO> searchByPostalCode(String postalCode) {
        return PostalAddressMapper.toResponseDTOs(postalAddressRepository.findByPostCode(postalCode));
    }

    public List<PostalAddressResponseDTO> searchByCityAndAddress(String cityCode, String searchText) {
        City city = cityRepository.findByCode(cityCode)
                .orElseThrow(() -> new ResourceNotFoundException("City not found"));
        searchText = searchText == null ? "" : searchText;
        return PostalAddressMapper.toResponseDTOs(
                postalAddressRepository.findByCityAndStreetNameOrNumber(city.getId(), searchText));
    }

    public PostalAddressResponseDTO saveAddress(PostalAddressRequestDTO addressRequestDTO) {
        City city = cityRepository.findByCode(addressRequestDTO.getCityCode())
                .orElseThrow(() -> new ResourceNotFoundException("City not found"));
        PostalAddress saved = postalAddressRepository.save(PostalAddressMapper.toEntity(addressRequestDTO, city));
        return PostalAddressMapper.toResponseDTO(saved);
    }
}
