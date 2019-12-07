package com.sep.koncentratorPlacanja.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipPlacanjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_casopisa",nullable = false, length = 255)
    private Long id_casopisa;

    @ElementCollection(targetClass = TipPlacanja.class)
    @JoinTable(name = "tipovi_placanja", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "tip_placanja", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<TipPlacanja> tipoviPlacanja = new ArrayList<>();

    public TipPlacanjaModel() {
    }

    public TipPlacanjaModel(Long id_casopisa, List<TipPlacanja> tipoviPlacanja) {
        this.id_casopisa = id_casopisa;
        this.tipoviPlacanja = tipoviPlacanja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_casopisa() {
        return id_casopisa;
    }

    public void setId_casopisa(Long id_casopisa) {
        this.id_casopisa = id_casopisa;
    }

    public List<TipPlacanja> getTipoviPlacanja() {
        return tipoviPlacanja;
    }

    public void setTipoviPlacanja(List<TipPlacanja> tipoviPlacanja) {
        this.tipoviPlacanja = tipoviPlacanja;
    }
}
