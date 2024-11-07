package org.baas.baascore.repository;

import org.baas.baascore.model.CoreTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoreTransactionRepository extends JpaRepository<CoreTransaction,Long> {
}
