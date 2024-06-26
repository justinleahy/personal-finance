package com.justinleahy.personalfinance.transaction;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    @Column(precision = 19, scale = 4)
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="vendor_id")
    private Vendor vendor;

    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    protected Transaction() {}

    public Transaction(String name, BigDecimal amount, Vendor vendor) {
        this.name = name;
        this.amount = amount;
        this.vendor = vendor;
        createdDateTime = LocalDateTime.now();
        lastModifiedDateTime = createdDateTime;
    }
}
