package com.example.search.postal.controllers;

import com.example.search.postal.dtos.PostalAddressRequestDTO;
import com.example.search.postal.dtos.PostalAddressResponseDTO;
import com.example.search.postal.mappers.PostalAddressMapper;
import com.example.search.postal.models.PostalAddress;
import com.example.search.postal.services.PostalAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class PostalAddressController {
    private final PostalAddressService postalAddressService;

    public PostalAddressController(PostalAddressService postalAddressService) {
        this.postalAddressService = postalAddressService;
    }
    /**
     * Endpoint to create a new postal address.
     * @param addressRequestDTO The request body containing address details.
     * @return The saved postal address response.
     */
    @PostMapping
    public ResponseEntity<PostalAddressResponseDTO> createPostalAddress(@Valid @RequestBody PostalAddressRequestDTO addressRequestDTO) {
        PostalAddress address = PostalAddressMapper.toEntity(addressRequestDTO);
        PostalAddress saveAddress = postalAddressService.saveAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PostalAddressMapper.toResponseDTO(saveAddress));
    }


    /**
     * Endpoint to search for postal addresses by postal code.
     * @param postalCode The postal code to search for.
     * @return List of postal addresses that match the postal code.
     */
    @GetMapping("/search/postalCode")
    public ResponseEntity<List<PostalAddressResponseDTO>> searchByPostalCode(@RequestParam String postalCode) {
        List<PostalAddress> addresses = postalAddressService.searchByPostalCode(postalCode);
        return ResponseEntity.ok(PostalAddressMapper.toResponseDTOs(addresses));
    }


    /**
     * Endpoint to search for postal addresses by city and address (area or street).
     * @param city The city to search for.
     * @param address The street or area to search for.
     * @return List of postal addresses that match the city and address.
     */
    @GetMapping("/search/cityAndAddress")
    public ResponseEntity<List<PostalAddressResponseDTO>> searchByCityAndAddress(@RequestParam String city, @RequestParam String address) {
        List<PostalAddress> addresses = postalAddressService.searchByCityAndAddress(city, address);
        return ResponseEntity.ok(PostalAddressMapper.toResponseDTOs(addresses));
    }
}
