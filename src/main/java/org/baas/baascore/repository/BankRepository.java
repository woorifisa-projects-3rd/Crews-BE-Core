package org.baas.baascore.repository;

import org.baas.baascore.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
    Optional<Bank> findByBankCode(String bankCode);
}
