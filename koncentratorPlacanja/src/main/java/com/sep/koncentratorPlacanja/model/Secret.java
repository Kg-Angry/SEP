package com.sep.koncentratorPlacanja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Secret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazivCasopisa;
    private Long merchantId;
    private String merchantPass;

    public Secret() {
    }

    public Secret(String nazivCasopisa, Long merchantId, String merchantPass) {
        this.nazivCasopisa = nazivCasopisa;
        this.merchantId = merchantId;
        this.merchantPass = merchantPass;
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPass() {
        return merchantPass;
    }

    public void setMerchantPass(String merchantPass) {
        this.merchantPass = merchantPass;
    }
}
