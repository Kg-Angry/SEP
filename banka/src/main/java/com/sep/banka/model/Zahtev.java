package com.sep.banka.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Zahtev {

    @Id
    @GeneratedValue
    private Long idZahtev;

    private Long paymentId;
    private String merchantUsername;//id prodavca
    private String merchantPassword;
    private BigDecimal amount; //iznos transakcije
    private Integer merchantOrderId; //prodavcev id transkcije
    private Timestamp merchantTimestamp; //timestamp transakcije
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

    @OneToMany(mappedBy = "zahtev", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ZahtevCasopisi> zahtevCasopisis = new HashSet<>();

    public Zahtev() {
    }

    public Long getIdZahtev() {
        return idZahtev;
    }

    public void setIdZahtev(Long idZahtev) {
        this.idZahtev = idZahtev;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getMerchantUsername() {
        return merchantUsername;
    }

    public void setMerchantUsername(String merchantUsername) {
        this.merchantUsername = merchantUsername;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(Integer merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public Timestamp getMerchantTimestamp() {
        return merchantTimestamp;
    }

    public void setMerchantTimestamp(Timestamp merchantTimestamp) {
        this.merchantTimestamp = merchantTimestamp;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailedUrl() {
        return failedUrl;
    }

    public void setFailedUrl(String failedUrl) {
        this.failedUrl = failedUrl;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }


    public Set<ZahtevCasopisi> getZahtevCasopisis() {
        return zahtevCasopisis;
    }

    public void setZahtevCasopisis(Set<ZahtevCasopisi> zahtevCasopisis) {
        this.zahtevCasopisis = zahtevCasopisis;
    }
}
