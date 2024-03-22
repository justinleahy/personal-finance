package com.justinleahy.personalfinance.transaction;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TransactionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String name;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    @OneToMany(mappedBy = "transactionCategory")
    private Set<TransactionsCategories> assignedTransactions = new HashSet<>();

    protected TransactionCategory() {}

    public TransactionCategory(String name) {
        this.name = name;
        this.createdDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = this.createdDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "TransactionCategory[id=%d, name='%s', createdDateTime='%s', lastModifiedDateTime='%s']",
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
        this.lastModifiedDateTime = LocalDateTime.now();
    }
}
