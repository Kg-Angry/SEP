package com.sep.banka.repository;

import com.sep.banka.model.Zahtev;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZahtevRepository extends JpaRepository<Zahtev,Long> {
    Zahtev findByPaymentId(Long paymentId);
}
