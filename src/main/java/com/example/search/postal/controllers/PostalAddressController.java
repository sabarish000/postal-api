package com.example.search.postal.controllers;

import com.example.search.postal.dtos.PostalAddressRequestDTO;
import com.example.search.postal.dtos.PostalAddressResponseDTO;
import com.example.search.postal.services.PostalAddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
     *
     * @param addressRequestDTO The request body containing address details.
     * @return The saved postal address response.
     */
    @PostMapping
    public ResponseEntity<PostalAddressResponseDTO> createPostalAddress(@Valid @RequestBody PostalAddressRequestDTO addressRequestDTO) {
        PostalAddressResponseDTO savedAddress = postalAddressService.saveAddress(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }


    /**
     * Endpoint to search for postal addresses by postal code.
     *
     * @param postalCode The postal code to search for.
     * @return List of postal addresses that match the postal code.
     */
    @GetMapping("/search/by-postal-code")
    public ResponseEntity<List<PostalAddressResponseDTO>> searchByPostalCode(@RequestParam(name="postal_code") String postalCode) {
        List<PostalAddressResponseDTO> addresses = postalAddressService.searchByPostalCode(postalCode);
        return ResponseEntity.ok(addresses);
    }


    /**
     * Endpoint to search for postal addresses by city and address (area or street).
     *
     * @param cityCode    The city code to search for.
     * @param searchText The street or area to search for.
     * @return List of postal addresses that match the city and street.
     */
    @GetMapping("/search/by-city-and-address")
    public ResponseEntity<List<PostalAddressResponseDTO>> searchByCityAndAddress(@NotBlank @RequestParam(name="city_code") String cityCode,
                                                                                 @RequestParam(name="search_text", required = false) String searchText) {
        List<PostalAddressResponseDTO> addresses = postalAddressService.searchByCityAndAddress(cityCode, searchText);
        return ResponseEntity.ok(addresses);
    }
}
