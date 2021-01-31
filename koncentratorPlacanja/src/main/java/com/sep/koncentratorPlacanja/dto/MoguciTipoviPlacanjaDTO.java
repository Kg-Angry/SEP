package com.sep.koncentratorPlacanja.dto;

import com.sep.koncentratorPlacanja.model.MoguciTipoviPlacanja;

public class MoguciTipoviPlacanjaDTO {

    private Long id;
    private String naziv;

    public MoguciTipoviPlacanjaDTO() {
    }

    public MoguciTipoviPlacanjaDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public MoguciTipoviPlacanjaDTO(MoguciTipoviPlacanja mtp)
    {
        this(mtp.getId(),mtp.getNaziv());
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
