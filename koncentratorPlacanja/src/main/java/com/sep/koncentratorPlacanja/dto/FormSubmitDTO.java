package com.sep.koncentratorPlacanja.dto;

import java.util.List;

public class FormSubmitDTO {
    private String casopisUsername;
    private String naziv; //banka,paypal,bitcoin
    private List<FormSubmissionDTO> fields;

    public FormSubmitDTO() {
    }

    public FormSubmitDTO(String casopisUsername, String naziv, List<FormSubmissionDTO> fields) {
        this.casopisUsername = casopisUsername;
        this.naziv = naziv;
        this.fields = fields;
    }

    public String getCasopisUsername() {
        return casopisUsername;
    }

    public void setCasopisUsername(String casopisUsername) {
        this.casopisUsername = casopisUsername;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<FormSubmissionDTO> getFields() {
        return fields;
    }

    public void setFields(List<FormSubmissionDTO> fields) {
        this.fields = fields;
    }
}
