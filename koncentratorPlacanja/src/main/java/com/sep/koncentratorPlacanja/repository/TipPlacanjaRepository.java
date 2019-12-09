package com.sep.koncentratorPlacanja.repository;

import com.sep.koncentratorPlacanja.model.TipPlacanjaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipPlacanjaRepository extends JpaRepository<TipPlacanjaModel, Long> {

    TipPlacanjaModel findByNaziv(String casopis);
}
