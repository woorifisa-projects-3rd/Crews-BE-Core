package org.baas.baascore.repository;

import org.baas.baascore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdentityCode(String identityCode);

    Optional<Customer> findByNameAndPhoneNum(String name, String phoneNum);

}
