package com.example.search.postal.repositories;

import com.example.search.postal.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCode(String code);
    List<City> findByNameContainingIgnoreCaseOrDistrictContainingIgnoreCaseOrStateContainingIgnoreCase(String name, String district, String state);
}
