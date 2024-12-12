package com.example.search.postal.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
/*
* @MappedSuperclass does not generate a table for this class
* But all these attributes should be there in tables of the subclasses
* */
@MappedSuperclass
public abstract class AuditRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime createdTime;
    @ManyToOne
    private User createdBy;
    private LocalDateTime updatedTime;
    @ManyToOne
    private User updatedBy;
}
