package com.sep.banka.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ZahtevCasopisi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merchantUsername;
    private BigDecimal cena;

    @ManyToOne
    @JoinColumn(name = "zahtevId")
    private Zahtev zahtev;

    public ZahtevCasopisi() {
    }

    public ZahtevCasopisi(String merchantUsername, BigDecimal cena, Zahtev zahtev) {
        this.merchantUsername = merchantUsername;
        this.cena = cena;
        this.zahtev = zahtev;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantUsername() {
        return merchantUsername;
    }

    public void setMerchantUsername(String merchantUsername) {
        this.merchantUsername = merchantUsername;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public Zahtev getZahtev() {
        return zahtev;
    }

    public void setZahtev(Zahtev zahtev) {
        this.zahtev = zahtev;
    }
}
