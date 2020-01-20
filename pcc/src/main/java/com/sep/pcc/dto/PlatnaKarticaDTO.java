package com.sep.pcc.dto;

import java.sql.Timestamp;

public class PlatnaKarticaDTO {

    private String pan;

    private String securityCode;

    private String cardHolderName;

    private String datumVazenja;

    private String merchantUsername;

    private Long paymentId;

    private int acquierID;

    private Timestamp acquirerTimestamp;

    private Long issuerId;

    private Timestamp issuerTimestamp;

    private String orderId;

    public PlatnaKarticaDTO() {
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(String datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public String getMerchantUsername() {
        return merchantUsername;
    }

    public void setMerchantUsername(String merchantUsername) {
        this.merchantUsername = merchantUsername;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public int getAcquierID() {
        return acquierID;
    }

    public void setAcquierID(int acquierID) {
        this.acquierID = acquierID;
    }

    public Timestamp getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public Long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    public Timestamp getIssuerTimestamp() {
        return issuerTimestamp;
    }

    public void setIssuerTimestamp(Timestamp issuerTimestamp) {
        this.issuerTimestamp = issuerTimestamp;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
