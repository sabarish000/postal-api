package com.example.search.postal.services;

import com.example.search.postal.models.PostalAddress;
import com.example.search.postal.repositories.PostalAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostalAddressService {
    private final PostalAddressRepository postalAddressRepository;

    public PostalAddressService(PostalAddressRepository postalAddressRepository) {
        this.postalAddressRepository = postalAddressRepository;
    }

    public List<PostalAddress> searchByPostalCode(String postalCode) {
        return postalAddressRepository.findByPostCode(postalCode);
    }

    public List<PostalAddress> searchByCityAndAddress(String city, String address) {
        return postalAddressRepository.findByCityIgnoreCaseAndStreetNameContainingIgnoreCase(city, address);
    }

    public PostalAddress saveAddress(PostalAddress address) {
        return postalAddressRepository.save(address);
    }
}
