package com.sep.banka.dto;

import java.math.BigDecimal;

public class NaucniCasopisDTO {

    private Long id;

    private String naziv;

    private BigDecimal cena;

    public NaucniCasopisDTO(Long id, String naziv, BigDecimal cena) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }
}
