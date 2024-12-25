package com.example.search.postal.controllers;

import com.example.search.postal.dtos.PostalAddressRequestDTO;
import com.example.search.postal.dtos.PostalAddressResponseDTO;
import com.example.search.postal.services.IPostalAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@Tag(name = "Postal Address Controller", description = "APIs to manage postal addresses")
public class PostalAddressController {
    // Decoupling the service from controller
    private final IPostalAddressService postalAddressService;

    public PostalAddressController(IPostalAddressService postalAddressService) {
        this.postalAddressService = postalAddressService;
    }

    /**
     * Endpoint to create a new postal address.
     *
     * @param addressRequestDTO The request body containing address details.
     * @return The saved postal address response.
     */
    @Operation(
            summary = "Create a new postal address",
            description = "Creates a new postal address and returns the saved address details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Postal address created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostalAddressResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(mediaType = "application/json")
            )
    })
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
    @Operation(
            summary = "Search postal addresses by postal code",
            description = "Finds postal addresses that match the provided postal code."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved matching addresses",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostalAddressResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No addresses found",
                    content = @Content(mediaType = "application/json")
            )
    })
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
    @Operation(
            summary = "Search postal addresses by city and street/area",
            description = "Finds postal addresses that match the provided city code and street/area."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved matching addresses",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostalAddressResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid city code or search text",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/search/by-city-and-address")
    public ResponseEntity<List<PostalAddressResponseDTO>> searchByCityAndAddress(@NotBlank @RequestParam(name="city_code") String cityCode,
                                                                                 @RequestParam(name="search_text", required = false) String searchText) {
        List<PostalAddressResponseDTO> addresses = postalAddressService.searchByCityAndAddress(cityCode, searchText);
        return ResponseEntity.ok(addresses);
    }
}
