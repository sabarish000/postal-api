package com.example.search.postal.controllers;

import com.example.search.postal.dtos.PostalAddressRequestDTO;
import com.example.search.postal.dtos.PostalAddressResponseDTO;
import com.example.search.postal.security.JwtTokenUtil;
import com.example.search.postal.security.TestSecurityConfig;
import com.example.search.postal.services.PostalAddressService;
import com.example.search.postal.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostalAddressController.class)
@Import(TestSecurityConfig.class)
public class PostalAddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostalAddressService postalAddressService;

    @MockitoBean
    private UserService userService;

    @InjectMocks
    private PostalAddressController postalAddressController;

    private PostalAddressRequestDTO requestDTO;
    private PostalAddressResponseDTO responseDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        requestDTO = new PostalAddressRequestDTO();
        requestDTO.setPostalCode("10115");
        requestDTO.setStreet("Main St");
        requestDTO.setCityCode("City-XX");
        responseDTO = PostalAddressResponseDTO.builder()
                .id(1L)
                .street("Main St")
                .city("Berlin")
                .postalCode("10115")
                .country("Germany")
                .build();
    }

    /**
     * Test cases for createPostalAddress endpoint
     */

    @Test
    @WithMockUser
    public void testCreatePostalAddress_Success() throws Exception {
        when(postalAddressService.saveAddress(Mockito.any(PostalAddressRequestDTO.class)))
                .thenReturn(responseDTO);
        String requestBody = objectMapper.writeValueAsString(requestDTO);
        mockMvc.perform(post("/api/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void testCreatePostalAddress_ValidationFailure() throws Exception {
        PostalAddressRequestDTO invalidRequest = new PostalAddressRequestDTO(); // Empty request
        when(postalAddressService.saveAddress(any(PostalAddressRequestDTO.class)))
                .thenReturn(responseDTO);

        String requestBody = objectMapper.writeValueAsString(invalidRequest);
        mockMvc.perform(post("/api/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }


    /**
     * Test cases for searchByPostalCode endpoint
     */

    @Test
    public void testSearchByPostalCode_Success() throws Exception {
        List<PostalAddressResponseDTO> responseDTOs = List.of(responseDTO);

        Mockito.when(postalAddressService.searchByPostalCode("10115"))
                .thenReturn(responseDTOs);

        mockMvc.perform(get("/api/address/search/by-postal-code")
                        .param("postal_code", "10115"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testSearchByPostalCode_Success_NoAddressesFound() throws Exception {
        Mockito.when(postalAddressService.searchByPostalCode("99999"))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/address/search/by-postal-code")
                        .param("postal_code", "99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testSearchByPostalCode_BadRequest_PostalCodeIsBlank() throws Exception {
        Mockito.when(postalAddressService.searchByPostalCode(null))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/address/search/by-postal-code"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test cases for searchByCityAndAddress endpoint
     */

    @Test
    public void testSearchByCityAndAddress_Success() throws Exception {
        List<PostalAddressResponseDTO> responseDTOs = List.of(responseDTO);

        Mockito.when(postalAddressService.searchByCityAndAddress("City-XX", "Main St"))
                .thenReturn(responseDTOs);

        mockMvc.perform(get("/api/address/search/by-city-and-address")
                        .param("city_code", "City-XX")
                        .param("search_text", "Main St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].street").value("Main St"))
                .andExpect(jsonPath("$[0].city").value("Berlin"))
                .andExpect(jsonPath("$[0].postalCode").value("10115"))
                .andExpect(jsonPath("$[0].country").value("Germany"));
    }

    @Test
    public void testSearchByCityAndAddress_Success_EmptySearchText() throws Exception {
        List<PostalAddressResponseDTO> responseDTOs = List.of(responseDTO);

        Mockito.when(postalAddressService.searchByCityAndAddress("City-XX", null))
                .thenReturn(responseDTOs);

        mockMvc.perform(get("/api/address/search/by-city-and-address")
                        .param("city_code", "City-XX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].street").value("Main St"))
                .andExpect(jsonPath("$[0].city").value("Berlin"))
                .andExpect(jsonPath("$[0].postalCode").value("10115"))
                .andExpect(jsonPath("$[0].country").value("Germany"));
    }

    @Test
    void testSearchByCityAndAddress_BadRequest_CityCodeIsBlank() throws Exception {
        List<PostalAddressResponseDTO> responseDTOs = List.of(responseDTO);

        Mockito.when(postalAddressService.searchByCityAndAddress(null, "xyz"))
                .thenReturn(responseDTOs);

        mockMvc.perform(get("/api/address/search/by-city-and-address")
                        .param("search_text", "xyz"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSearchByCityAndAddress_Success_NoAddressesFound() throws Exception {
        when(postalAddressService.searchByCityAndAddress("CityABC", "Unknown St"))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/address/search/by-city-and-address")
                        .param("city_code", "CityABC")
                        .param("search_text", "Unknown St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
