package com.sep.koncentratorPlacanja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BitCoinSecret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String casopisUsername;

    private String token;

    public BitCoinSecret() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCasopisUsername() {
        return casopisUsername;
    }

    public void setCasopisUsername(String casopisUsername) {
        this.casopisUsername = casopisUsername;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
