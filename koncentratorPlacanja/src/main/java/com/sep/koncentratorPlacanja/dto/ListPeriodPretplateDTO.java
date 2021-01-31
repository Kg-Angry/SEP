package com.sep.koncentratorPlacanja.dto;

import java.util.HashSet;
import java.util.Set;

public class ListPeriodPretplateDTO {

    private Set<PeriodPretplateDTO> list_period = new HashSet<>();

    public ListPeriodPretplateDTO() {
    }

    public ListPeriodPretplateDTO(Set<PeriodPretplateDTO> list_period) {
        this.list_period = list_period;
    }

    public Set<PeriodPretplateDTO> getList_period() {
        return list_period;
    }

    public void setList_period(Set<PeriodPretplateDTO> list_period) {
        this.list_period = list_period;
    }
}
