package com.example.paypal.PayPal.Model;

public class PayPalProfile {

    private String paymentId;
    private String payerID;

    public PayPalProfile() {
    }

    public PayPalProfile(String paymentId, String payerID) {
        this.paymentId = paymentId;
        this.payerID = payerID;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }
}
