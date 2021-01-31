package com.sep.koncentratorPlacanja.dto;

import java.util.HashSet;
import java.util.Set;

public class PeriodPretplateDTO {

    private String period;
    private Set<Integer> ciklus = new HashSet<>();

    public PeriodPretplateDTO() {
    }

    public PeriodPretplateDTO(String period, Set<Integer> ciklus) {
        this.period = period;
        this.ciklus = ciklus;
    }

    public String getPeriod() {
        return period;
    }

    public Set<Integer> getCiklus() {
        return ciklus;
    }
}
