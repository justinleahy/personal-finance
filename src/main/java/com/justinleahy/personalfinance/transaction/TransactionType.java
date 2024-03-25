package com.justinleahy.personalfinance.transaction;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    protected TransactionType() {}

    public TransactionType(String name) {
        this.name = name;
        this.createdDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "TransactionType[id=%d, name='%s', createdDateTime='%s', lastModifiedDateTime='%s']",
                id, name, createdDateTime, lastModifiedDateTime
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setName(String name) {
        this.name = name;
        lastModifiedDateTime = LocalDateTime.now();
    }
}
