package com.example.paypal.PayPal.DTO;

public class FormSubmissionDTO {
    private String fieldId;
    private String fieldValues;

    public FormSubmissionDTO() {
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(String fieldValues) {
        this.fieldValues = fieldValues;
    }
}
