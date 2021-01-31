package com.sep.koncentratorPlacanja.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transakcije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="idTransakcije",nullable = false, length = 255)
    private String idTransakcije;

    @Column(name="uuid", nullable = false, length = 255)
    private String uuid;

    @Column(name="naziv", nullable = false, length = 255)
    private String naziv;

    @Column(name="vremeKreiranjaTransakcije", length = 255)
    private String vremeKreiranjaTransakcije;

    @Column(name="status", nullable = false, length = 255)
    private String status;

    @Column(name="uplatilac", nullable = false, length = 255)
    private String uplatilac;

    @Column(name="tipPlacanja", nullable = false, length = 255)
    private String tipPlacanja;

    @Column(name="order_id", nullable = false, length = 255)
    private String orderId;

    @Column(name="cena",nullable = false, length = 255)
    private Double cena;

    public Transakcije() {
    }

    public Transakcije(String idTransakcije, String uuid, String naziv, String vremeKreiranjaTransakcije, String status, String uplatilac, String tipPlacanja, String orderId, Double cena) {
        this.idTransakcije = idTransakcije;
        this.uuid = uuid;
        this.naziv = naziv;
        this.vremeKreiranjaTransakcije = vremeKreiranjaTransakcije;
        this.status = status;
        this.uplatilac = uplatilac;
        this.tipPlacanja = tipPlacanja;
        this.orderId = orderId;
        this.cena = cena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTransakcije() {
        return idTransakcije;
    }

    public void setIdTransakcije(String idTransakcije) {
        this.idTransakcije = idTransakcije;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVremeKreiranjaTransakcije() {
        return vremeKreiranjaTransakcije;
    }

    public void setVremeKreiranjaTransakcije(String vremeKreiranjaTransakcije) {
        this.vremeKreiranjaTransakcije = vremeKreiranjaTransakcije;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUplatilac() {
        return uplatilac;
    }

    public void setUplatilac(String uplatilac) {
        this.uplatilac = uplatilac;
    }

    public String getTipPlacanja() {
        return tipPlacanja;
    }

    public void setTipPlacanja(String tipPlacanja) {
        this.tipPlacanja = tipPlacanja;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }
}
