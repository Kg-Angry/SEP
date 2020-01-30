package com.sep.koncentratorPlacanja.repository;

import com.sep.koncentratorPlacanja.model.BankaSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankaSecretRepository extends JpaRepository<BankaSecret,Long> {

BankaSecret findByCasopisUsername(String casopisUsername);

}
