package com.example.search.postal.mappers;

import com.example.search.postal.dtos.PostalAddressRequestDTO;
import com.example.search.postal.dtos.PostalAddressResponseDTO;
import com.example.search.postal.models.City;
import com.example.search.postal.models.PostalAddress;

import java.util.List;
import java.util.stream.Collectors;

public class PostalAddressMapper {

    // Convert List<PostalAddress> to List<PostalAddressResponseDTO>
    public static List<PostalAddressResponseDTO> toResponseDTOs(List<PostalAddress> addresses) {
        return addresses.stream()
                .map(PostalAddressMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public static PostalAddressResponseDTO toResponseDTO(PostalAddress address) {
        return PostalAddressResponseDTO.builder()
                .postalCode(address.getPostCode())
                .street(address.getStreetName())
                .number(address.getNumber())
                .city(address.getCity().getName())
                .region(address.getCity().getRegion())
                .district(address.getCity().getDistrict())
                .state(address.getCity().getState())
                .country(address.getCity().getCountry())
                .build();
    }

    // Convert PostalAddressRequestDTO to PostalAddress
    public static PostalAddress toEntity(PostalAddressRequestDTO dto, City city) {
        return new PostalAddress(dto.getPostalCode(), dto.getStreet(), dto.getNumber(), city);
    }
}
