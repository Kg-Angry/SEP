package com.sep.koncentratorPlacanja.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class PlatilacDTO {
    private Double cena;

    private String korisnicko_ime_platioca;

    private String lozinka_platioca;

    private Integer id_porudzbine;

    private Timestamp vremensko_ogranicenje;

    private String uspesnaRedirekcija;

    private String neuspesnaRedirekcija;

    private String pogresnaRedirekcija;

    private String naziv_casopisa;

    private String paymentId;

    private String payerID;

    private String token;

    private String period;

    private String rate;

    public PlatilacDTO() {
    }

    public PlatilacDTO(Double cena, String korisnicko_ime_platioca, String lozinka_platioca, Integer id_porudzbine, Timestamp vremensko_ogranicenje, String uspesnaRedirekcija, String neuspesnaRedirekcija, String pogresnaRedirekcija, String naziv_casopisa,String paymentId,String payerID, String token, String period, String rate) {
        this.cena = cena;
        this.korisnicko_ime_platioca = korisnicko_ime_platioca;
        this.lozinka_platioca = lozinka_platioca;
        this.id_porudzbine = id_porudzbine;
        this.vremensko_ogranicenje = vremensko_ogranicenje;
        this.uspesnaRedirekcija = uspesnaRedirekcija;
        this.neuspesnaRedirekcija = neuspesnaRedirekcija;
        this.pogresnaRedirekcija = pogresnaRedirekcija;
        this.naziv_casopisa = naziv_casopisa;
        this.paymentId = paymentId;
        this.payerID = payerID;
        this.token = token;
        this.period = period;
        this.rate = rate;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getKorisnicko_ime_platioca() {
        return korisnicko_ime_platioca;
    }

    public void setKorisnicko_ime_platioca(String korisnicko_ime_platioca) {
        this.korisnicko_ime_platioca = korisnicko_ime_platioca;
    }

    public String getLozinka_platioca() {
        return lozinka_platioca;
    }

    public void setLozinka_platioca(String lozinka_platioca) {
        this.lozinka_platioca = lozinka_platioca;
    }

    public Integer getId_porudzbine() {
        return id_porudzbine;
    }

    public void setId_porudzbine(Integer id_porudzbine) {
        this.id_porudzbine = id_porudzbine;
    }

    public Timestamp getVremensko_ogranicenje() {
        return vremensko_ogranicenje;
    }

    public void setVremensko_ogranicenje(Timestamp vremensko_ogranicenje) {
        this.vremensko_ogranicenje = vremensko_ogranicenje;
    }

    public String getUspesnaRedirekcija() {
        return uspesnaRedirekcija;
    }

    public void setUspesnaRedirekcija(String uspesnaRedirekcija) {
        this.uspesnaRedirekcija = uspesnaRedirekcija;
    }

    public String getNeuspesnaRedirekcija() {
        return neuspesnaRedirekcija;
    }

    public void setNeuspesnaRedirekcija(String neuspesnaRedirekcija) {
        this.neuspesnaRedirekcija = neuspesnaRedirekcija;
    }

    public String getPogresnaRedirekcija() {
        return pogresnaRedirekcija;
    }

    public void setPogresnaRedirekcija(String pogresnaRedirekcija) {
        this.pogresnaRedirekcija = pogresnaRedirekcija;
    }

    public String getNaziv_casopisa() {
        return naziv_casopisa;
    }

    public void setNaziv_casopisa(String naziv_casopisa) {
        this.naziv_casopisa = naziv_casopisa;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
