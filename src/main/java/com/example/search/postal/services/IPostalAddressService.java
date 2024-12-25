package com.example.search.postal.services;

import com.example.search.postal.dtos.PostalAddressRequestDTO;
import com.example.search.postal.dtos.PostalAddressResponseDTO;

import java.util.List;

public interface IPostalAddressService {
    List<PostalAddressResponseDTO> searchByPostalCode(String postalCode);
    List<PostalAddressResponseDTO> searchByCityAndAddress(String cityCode, String searchText);
    PostalAddressResponseDTO saveAddress(PostalAddressRequestDTO addressRequestDTO);

}
