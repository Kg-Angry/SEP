package com.sep.koncentratorPlacanja.dto;

import com.sep.koncentratorPlacanja.model.TipPlacanja;
import com.sep.koncentratorPlacanja.model.TipPlacanjaModel;

import java.util.ArrayList;
import java.util.List;

public class TipPlacanjaDTO {
    private Long id;
    private String naziv;
    private List<TipPlacanja> tipoviPlacanja = new ArrayList<>();

    public TipPlacanjaDTO() {
    }

    public TipPlacanjaDTO(Long id, String naziv_casopisa, List<TipPlacanja> tipoviPlacanja) {
        this.id = id;
        this.naziv = naziv_casopisa;
        this.tipoviPlacanja = tipoviPlacanja;
    }

    public TipPlacanjaDTO(TipPlacanjaModel tpm)
    {
        this(tpm.getId(),tpm.getNaziv(),tpm.getTipoviPlacanja());
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
