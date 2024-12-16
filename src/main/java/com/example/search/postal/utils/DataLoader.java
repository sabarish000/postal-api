package com.example.search.postal.utils;

import com.example.search.postal.repositories.CityRepository;
import com.example.search.postal.repositories.PostalAddressRepository;
import com.example.search.postal.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner loadData(CityRepository cityRepository, PostalAddressRepository postalAddressRepository, UserRepository userRepository) {
        return args -> {
            MockDataUtils.cities.forEach(cityRepository::save);
            MockDataUtils.POSTAL_ADDRESS.forEach(postalAddressRepository::save);
            MockDataUtils.USERS.forEach(userRepository::save);
        };
    }
}
