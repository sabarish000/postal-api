package com.example.search.postal.services;

import com.example.search.postal.models.PostalAddress;
import com.example.search.postal.repositories.PostalAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostalAddressService {
    private final PostalAddressRepository postalAddressRepository;

    public PostalAddressService(PostalAddressRepository postalAddressRepository) {
        this.postalAddressRepository = postalAddressRepository;
    }

    public List<PostalAddress> searchByPostalCode(String postalCode) {
        return postalAddressRepository.findByPostCode(postalCode);
//                postalAddresses.stream()
//                .filter(postalAddress -> postalAddress.getPostCode().equalsIgnoreCase(postalCode))
//                .collect(Collectors.toList());
    }

    public List<PostalAddress> searchByCityAndAddress(String city, String address) {
        return postalAddressRepository.findByCityIgnoreCaseAndStreetNameContainingIgnoreCase(city, address);
//                postalAddresses.stream()
//                .filter(postalAddress -> postalAddress.getCity().equalsIgnoreCase(city) && postalAddress.getStreetName().toLowerCase().contains(address.toLowerCase()))
//                .collect(Collectors.toList());
    }

    public PostalAddress saveAddress(PostalAddress address) {
        return postalAddressRepository.save(address);
//        return address;
    }
}
