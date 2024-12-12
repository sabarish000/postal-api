package com.example.search.postal.repositories;

import com.example.search.postal.models.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostalAddressRepository extends JpaRepository< PostalAddress, Long> {
    List<PostalAddress> findByPostCode(String postCode);

    List<PostalAddress> findByCityIgnoreCaseAndStreetNameContainingIgnoreCase(String city, String streetName);
}
