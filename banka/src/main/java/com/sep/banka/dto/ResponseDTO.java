package com.sep.banka.dto;

import java.sql.Timestamp;

public class ResponseDTO {

    private String statusTransakcije;
    private String MerchantUsername;
    private int AcquirerId;
    private Timestamp acquirerTimestamp;
    private Long PaymentId;

    public ResponseDTO() {
    }

    public String getStatusTransakcije() {
        return statusTransakcije;
    }

    public void setStatusTransakcije(String statusTransakcije) {
        this.statusTransakcije = statusTransakcije;
    }

    public String getMerchantUsername() {
        return MerchantUsername;
    }

    public void setMerchantUsername(String merchantUsername) {
        MerchantUsername = merchantUsername;
    }

    public int getAcquirerId() {
        return AcquirerId;
    }

    public void setAcquirerId(int acquirerId) {
        AcquirerId = acquirerId;
    }

    public Timestamp getAcquirerTimestamp() {
        return acquirerTimestamp;
    }

    public void setAcquirerTimestamp(Timestamp acquirerTimestamp) {
        this.acquirerTimestamp = acquirerTimestamp;
    }

    public Long getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(Long paymentId) {
        PaymentId = paymentId;
    }
}
