package com.justinleahy.personalfinance.user;

import com.justinleahy.personalfinance.account.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userName;
    private String passwordHash;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastModifiedDateTime;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<>();

    protected User() {}

    public User(String firstName, String lastName, String userName, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.creationDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = this.creationDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, firstName='%s', lastName='%s', userName='%s', creationDateTime='%s', lastModifiedDateTime='%s']",
                id, firstName, lastName, userName, creationDateTime, lastModifiedDateTime
        );
    }

    public String toJSON() {
        return String.format(
                        "{" +
                                "\"firstName\":\"%s\"," +
                                "\"lastName\":\"%s\"," +
                                "\"userName\":\"%s\"," +
                                "\"passwordHash\":\"%s\"" +
                        "}",
            firstName, lastName, userName, passwordHash
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public Set<Account> getAccounts() { return accounts; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        lastModifiedDateTime = LocalDateTime.now();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        lastModifiedDateTime = LocalDateTime.now();
    }

    public void setUserName(String userName) {
        this.userName = userName;
        lastModifiedDateTime = LocalDateTime.now();
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        lastModifiedDateTime = LocalDateTime.now();
    }
}
