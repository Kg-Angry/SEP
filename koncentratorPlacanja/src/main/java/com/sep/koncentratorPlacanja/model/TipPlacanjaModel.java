package com.sep.koncentratorPlacanja.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipPlacanjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naziv_casopisa",nullable = false, length = 255)
    private String naziv;

    @ElementCollection(targetClass = TipPlacanja.class)
    @JoinTable(name = "tipovi_placanja", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "tip_placanja", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<TipPlacanja> tipoviPlacanja = new ArrayList<>();

    public TipPlacanjaModel() {
    }

    public TipPlacanjaModel(String naziv_casopisa, List<TipPlacanja> tipoviPlacanja) {
        this.naziv = naziv_casopisa;
        this.tipoviPlacanja = tipoviPlacanja;
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

    public List<TipPlacanja> getTipoviPlacanja() {
        return tipoviPlacanja;
    }

    public void setTipoviPlacanja(List<TipPlacanja> tipoviPlacanja) {
        this.tipoviPlacanja = tipoviPlacanja;
    }
}
