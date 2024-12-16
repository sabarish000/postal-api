package com.example.search.postal.repositories;

import com.example.search.postal.models.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostalAddressRepository extends JpaRepository< PostalAddress, Long> {
    List<PostalAddress> findByPostCode(String postCode);

    @Query("""
                    SELECT p FROM PostalAddress p
                    WHERE p.city.id = :cityId
                    AND (
                        LOWER(p.streetName) LIKE LOWER(CONCAT('%', :searchText, '%'))
                        OR LOWER(p.number) LIKE LOWER(CONCAT('%', :searchText, '%'))
                    )
            """)
    List<PostalAddress> findByCityAndStreetNameOrNumber(@Param("cityId") Long cityId,
                                                        @Param("searchText") String searchText);
}
