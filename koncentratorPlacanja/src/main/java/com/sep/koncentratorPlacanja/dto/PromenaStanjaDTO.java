package com.sep.koncentratorPlacanja.dto;

import java.util.ArrayList;
import java.util.List;

public class PromenaStanjaDTO {

    private List<String> idTransakcija;

    public PromenaStanjaDTO() {
        this.idTransakcija=new ArrayList<>();
    }

    public List<String> getIdTransakcija() {
        return idTransakcija;
    }

    public void setIdTransakcija(List<String> idTransakcija) {
        this.idTransakcija = idTransakcija;
    }
}
