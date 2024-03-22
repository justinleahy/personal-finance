package com.justinleahy.personalfinance.transaction;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String name;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastModifiedDateTime;

    @OneToMany(mappedBy = "vendor")
    private Set<Transaction> transactions;

    protected Vendor() {}

    public Vendor(String name) {
        this.name = name;
        this.creationDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = this.creationDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Vendor[id=%d, name='%s', creationDateTime='%s', lastModifiedDateTime='%s']",
                id, name, creationDateTime, lastModifiedDateTime
        );
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}
