package com.example.search.postal.services;

import com.example.search.postal.models.PostalAddress;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostalAddressService {
    private final List<PostalAddress> postalAddresses;

    public PostalAddressService() {
        postalAddresses = MockDataUtils.POSTAL_ADDRESS;
    }

    public List<PostalAddress> searchByPostalCode(String postalCode) {
        return postalAddresses.stream()
                .filter(postalAddress -> postalAddress.getPostCode().equalsIgnoreCase(postalCode))
                .collect(Collectors.toList());
    }

    public List<PostalAddress> searchByCityAndAddress(String city, String address) {
        return postalAddresses.stream()
                .filter(postalAddress -> postalAddress.getCity().equalsIgnoreCase(city) && postalAddress.getStreetName().toLowerCase().contains(address.toLowerCase()))
                .collect(Collectors.toList());
    }

    public PostalAddress saveAddress(PostalAddress address) {
        postalAddresses.add(address);
        return address;
    }
}
