package com.sep.koncentratorPlacanja.repository;

import com.sep.koncentratorPlacanja.model.BitCoinSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitCoinSecretRepository extends JpaRepository<BitCoinSecret, Long> {
}
