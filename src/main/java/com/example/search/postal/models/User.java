package com.example.search.postal.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="users")
public class User extends AuditRecord{
    private String username;
    private String password;
}
