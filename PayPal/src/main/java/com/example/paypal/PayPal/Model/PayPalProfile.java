package com.example.paypal.PayPal.Model;

public class PayPalProfile {

    private String paymentId;
    private String payerID;
    private String orderId;

    public PayPalProfile() {
    }

    public PayPalProfile(String paymentId, String payerID, String order_id) {
        this.paymentId = paymentId;
        this.payerID = payerID;
        this.orderId = order_id;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
