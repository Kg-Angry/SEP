package com.example.paypal.PayPal.DTO;

import com.example.paypal.PayPal.Model.TopSecretData;

public class TopSecretDataDTO {

    private Long id;
    private String nazivCasopisa;
    private String clientId;
    private String clientSecret;

    public TopSecretDataDTO(Long id, String nazivCasopisa, String clientId, String clientSecret) {
        this.id = id;
        this.nazivCasopisa = nazivCasopisa;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public TopSecretDataDTO(TopSecretData t)
    {
        this(t.getId(),t.getNazivCasopisa(),t.getClientId(),t.getClientSecret());
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
