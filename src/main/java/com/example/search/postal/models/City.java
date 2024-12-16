package com.example.search.postal.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cities")
public class City extends AuditRecord{
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String name;
    private String region;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;
}
