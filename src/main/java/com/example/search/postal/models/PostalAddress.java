package com.example.search.postal.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostalAddress extends AuditRecord{
    private String postCode;
    private String streetName;
    private String number;
    private String city;
    private String region;
    private String district;
    private String state;
    private String country;
}
