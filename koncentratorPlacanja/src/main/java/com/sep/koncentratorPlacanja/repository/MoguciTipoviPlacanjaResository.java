package com.sep.koncentratorPlacanja.repository;

import com.sep.koncentratorPlacanja.model.MoguciTipoviPlacanja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoguciTipoviPlacanjaResository extends JpaRepository<MoguciTipoviPlacanja, Long> {

    MoguciTipoviPlacanja findByNaziv(String naziv);
}
