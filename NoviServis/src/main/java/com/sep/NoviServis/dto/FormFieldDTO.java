package com.sep.NoviServis.dto;

public class FormFieldDTO {

    private String fieldLabel;
    private String fieldTypeName;
    private String defaultValue;
    private String value;


    public FormFieldDTO() {
    }

    public FormFieldDTO(String fieldLabel, String fieldTypeName, String defaultValue, String value) {
        this.fieldLabel = fieldLabel;
        this.fieldTypeName = fieldTypeName;
        this.defaultValue = defaultValue;
        this.value = value;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
