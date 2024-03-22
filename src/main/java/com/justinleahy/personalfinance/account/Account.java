package com.justinleahy.personalfinance.account;

import com.justinleahy.personalfinance.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nickname;
    private int accountType;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastModifiedDateTime;

    protected Account() {}

    public Account(String nickname, int accountType, User user) {
        this.nickname = nickname;
        this.accountType = accountType;
        this.user = user;
        this.creationDateTime = LocalDateTime.now();
        this.lastModifiedDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[id=%d, nickname='%s', accountType='%s', user='%s', creationDateTime='%s', lastModifiedDateTime='%s']",
                id, nickname, accountType, user, creationDateTime, lastModifiedDateTime
        );
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAccountType() {
        return accountType;
    }

    public User getUser() {
        return user;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.lastModifiedDateTime = LocalDateTime.now();
    }
}
