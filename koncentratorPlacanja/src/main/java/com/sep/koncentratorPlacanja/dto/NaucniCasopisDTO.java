package com.sep.koncentratorPlacanja.dto;

public class NaucniCasopisDTO {

    private Long id;

    private String naziv;

    private Double cena;

    public NaucniCasopisDTO(Long id, String naziv, Double cena) {
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

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
