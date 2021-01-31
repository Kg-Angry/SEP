package com.example.paypal.PayPal.DTO;

import com.example.paypal.PayPal.Model.PayPalProfile;

public class PayPalProfileDTO {

    private String paymentId;
    private String payerID;
    private String orderId;

    public PayPalProfileDTO() {
    }

    public PayPalProfileDTO(String paymentId, String payerID, String orderId) {
        this.paymentId = paymentId;
        this.payerID = payerID;
        this.orderId = orderId;
    }

    public PayPalProfileDTO(PayPalProfile p)
    {
        this(p.getPaymentId(),p.getPayerID(),p.getOrderId());
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
