package com.sep.banka.dto;

public class PaymentDTO {
    private Long paymentID;
    private String paymentUrl;

    public PaymentDTO() {
    }

    public PaymentDTO(Long paymentID, String paymentUrl) {
        this.paymentID = paymentID;
        this.paymentUrl = paymentUrl;
    }

    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}
