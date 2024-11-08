package org.baas.baascore.repository;

import org.baas.baascore.model.Card;
import org.baas.baascore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findByCustomerAndCardStatusTrue(Customer customer);
}
