package com.example.paypal.PayPal.Model;

import java.sql.Timestamp;

public class Platilac {

    private Double cena;

    private String korisnicko_ime_platioca;

    private String lozinka_platioca;

    private Integer id_porudzbine;

    private Timestamp vremensko_ogranicenje;

    private String uspesnaRedirekcija;

    private String neuspesnaRedirekcija;

    private String pogresnaRedirekcija;

    private String naziv_casopisa;

    private String client_id;

    private String client_secret;

    private String token;

    private String period;

    private String rate; //na koliki period? npr: period je mesec a rate je 12 pa znaci na celu godinu se razvlaci

    public Platilac() {
    }

    public Platilac(Double cena, String korisnicko_ime_platioca, String lozinka_platioca, Integer id_porudzbine, Timestamp vremensko_ogranicenje, String uspesnaRedirekcija, String neuspesnaRedirekcija, String pogresnaRedirekcija, String naziv_casopisa, String client_id, String client_secret, String token, String period, String rate) {
        this.cena = cena;
        this.korisnicko_ime_platioca = korisnicko_ime_platioca;
        this.lozinka_platioca = lozinka_platioca;
        this.id_porudzbine = id_porudzbine;
        this.vremensko_ogranicenje = vremensko_ogranicenje;
        this.uspesnaRedirekcija = uspesnaRedirekcija;
        this.neuspesnaRedirekcija = neuspesnaRedirekcija;
        this.pogresnaRedirekcija = pogresnaRedirekcija;
        this.naziv_casopisa = naziv_casopisa;
        this.client_id = client_id;
        this.client_secret = client_secret;
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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
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
