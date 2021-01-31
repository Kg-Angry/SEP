package com.example.paypal.PayPal.DTO;

import java.util.ArrayList;
import java.util.List;

public class PromenaStanjaDTO {

    private List<String> idTransakcija;
    private List<Long> idPlaceno;

    public PromenaStanjaDTO() {
        this.idTransakcija = new ArrayList<>();
        this.idPlaceno = new ArrayList<>();
    }

    public List<String> getIdTransakcija() {
        return idTransakcija;
    }

    public void setIdTransakcija(List<String> idTransakcija) {
        this.idTransakcija = idTransakcija;
    }

    public List<Long> getIdPlaceno() {
        return idPlaceno;
    }

    public void setIdPlaceno(List<Long> idPlaceno) {
        this.idPlaceno = idPlaceno;
    }
}
