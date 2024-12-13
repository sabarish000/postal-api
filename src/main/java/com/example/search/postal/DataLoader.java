package com.example.search.postal;

import com.example.search.postal.repositories.PostalAddressRepository;
import com.example.search.postal.repositories.UserRepository;
import com.example.search.postal.services.MockDataUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner loadData(PostalAddressRepository postalAddressRepository, UserRepository userRepository) {
        return args -> {
            MockDataUtils.POSTAL_ADDRESS.forEach(postalAddressRepository::save);
            MockDataUtils.USERS.forEach(userRepository::save);
        };
    }
}
