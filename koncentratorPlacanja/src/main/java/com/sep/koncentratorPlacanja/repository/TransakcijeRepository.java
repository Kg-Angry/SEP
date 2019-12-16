package com.sep.koncentratorPlacanja.repository;

import com.sep.koncentratorPlacanja.model.Transakcije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransakcijeRepository extends JpaRepository<Transakcije, Long> {

    Transakcije findByNaziv (String naziv);

    Transakcije findByOrderId (String order_id);

}
