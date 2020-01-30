package com.sep.koncentratorPlacanja.dto;

public class FormSubmissionDTO {
    private String fieldId;
    private String fieldValue;

    public FormSubmissionDTO() {
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
