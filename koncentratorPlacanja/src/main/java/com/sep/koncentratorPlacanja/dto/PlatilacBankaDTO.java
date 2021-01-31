package com.sep.koncentratorPlacanja.dto;

import java.sql.Timestamp;
import java.util.Set;

public class PlatilacBankaDTO {

    private String korisnicko_ime_platioca;

    private String lozinka_platioca;

    private Integer id_porudzbine;

    private Timestamp vremensko_ogranicenje;

    private String uspesnaRedirekcija;

    private String neuspesnaRedirekcija;

    private String pogresnaRedirekcija;

    private Set<NaucniCasopisDTO> nazivi_casopisa;

    private Set<BankaSecretDTO> bankaSecret;

    private String naziv_radova;

    public PlatilacBankaDTO(String korisnicko_ime_platioca, String lozinka_platioca, Integer id_porudzbine, Timestamp vremensko_ogranicenje, String uspesnaRedirekcija, String neuspesnaRedirekcija, String pogresnaRedirekcija, Set<NaucniCasopisDTO> naziv_casopisa, String nazivRadova) {
        this.korisnicko_ime_platioca = korisnicko_ime_platioca;
        this.lozinka_platioca = lozinka_platioca;
        this.id_porudzbine = id_porudzbine;
        this.vremensko_ogranicenje = vremensko_ogranicenje;
        this.uspesnaRedirekcija = uspesnaRedirekcija;
        this.neuspesnaRedirekcija = neuspesnaRedirekcija;
        this.pogresnaRedirekcija = pogresnaRedirekcija;
        this.nazivi_casopisa = naziv_casopisa;
        this.naziv_radova=nazivRadova;
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

    public Set<NaucniCasopisDTO> getNazivi_casopisa() {
        return nazivi_casopisa;
    }

    public void setNazivi_casopisa(Set<NaucniCasopisDTO> naziv_casopisa) {
        this.nazivi_casopisa = naziv_casopisa;
    }

    public Set<BankaSecretDTO> getBankaSecret() {
        return bankaSecret;
    }

    public void setBankaSecret(Set<BankaSecretDTO> bankaSecret) {
        this.bankaSecret = bankaSecret;
    }

    public String getNaziv_radova() {
        return naziv_radova;
    }

    public void setNaziv_radova(String naziv_radova) {
        this.naziv_radova = naziv_radova;
    }
}
