package com.example.paypal.PayPal.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
public class TopSecretData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="naziv_casopisa", nullable = false, length = 255)
    private String nazivCasopisa;
    @Column(name="clientId", nullable = false, length = 255)
    private String clientId;
    @Column(name="clientSecret", nullable = false, length = 255)
    private String clientSecret;

    public TopSecretData() {
    }

    public TopSecretData(String naziv_casopisa, String clientId, String clientSecret)
    {
        this.nazivCasopisa=naziv_casopisa;
        this.clientId=clientId;
        this.clientSecret=clientSecret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivCasopisa() {
        return nazivCasopisa;
    }

    public void setNazivCasopisa(String nazivCasopisa) {
        this.nazivCasopisa = nazivCasopisa;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
