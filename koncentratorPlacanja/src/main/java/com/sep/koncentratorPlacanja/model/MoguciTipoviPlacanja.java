package com.sep.koncentratorPlacanja.model;

import javax.persistence.*;

@Entity
public class MoguciTipoviPlacanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="naziv",nullable = false, length = 255)
    private String naziv;

    public MoguciTipoviPlacanja() {
    }

    public MoguciTipoviPlacanja(String naziv) {
        this.naziv = naziv;
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
}
