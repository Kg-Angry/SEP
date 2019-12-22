package com.sep.banka.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ZahtevDTO {

    private String merchantUsername;//id prodavca
    private String merchantPassword;
    private BigDecimal amount; //iznos transakcije
    private Integer merchantOrderId; //prodavcev id transkcije
    private Timestamp merchantTimestamp; //timestamp transakcije
    private String successUrl;
    private String failedUrl;
    private String errorUrl;

    public ZahtevDTO() {
    }

    public ZahtevDTO(String merchantUsername, String merchantPassword, BigDecimal amount, Integer merchantOrderId, Timestamp merchantTimestamp, String successUrl, String failedUrl, String errorUrl) {
        this.merchantUsername = merchantUsername;
        this.merchantPassword = merchantPassword;
        this.amount = amount;
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.successUrl = successUrl;
        this.failedUrl = failedUrl;
        this.errorUrl = errorUrl;
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
}
