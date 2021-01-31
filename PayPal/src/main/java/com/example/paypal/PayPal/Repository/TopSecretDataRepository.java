package com.example.paypal.PayPal.Repository;

import com.example.paypal.PayPal.Model.TopSecretData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopSecretDataRepository extends JpaRepository<TopSecretData,Long> {

    TopSecretData findByNazivCasopisa(String naziv);
}
