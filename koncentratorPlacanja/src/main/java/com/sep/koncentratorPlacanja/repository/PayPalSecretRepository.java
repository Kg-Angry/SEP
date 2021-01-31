package com.sep.koncentratorPlacanja.repository;

import com.sep.koncentratorPlacanja.model.PayPalSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayPalSecretRepository extends JpaRepository<PayPalSecret,Long> {
}
