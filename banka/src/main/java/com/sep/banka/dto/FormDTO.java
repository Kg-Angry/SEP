package com.sep.banka.dto;

import java.util.ArrayList;
import java.util.List;

public class FormDTO {

    private String naziv;
    private List<FormFieldDTO> fields;

    public FormDTO() {
        this.fields = new ArrayList<>();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<FormFieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<FormFieldDTO> fields) {
        this.fields = fields;
    }
}
