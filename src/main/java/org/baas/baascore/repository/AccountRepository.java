package org.baas.baascore.repository;

import org.baas.baascore.model.Account;
import org.baas.baascore.model.Customer;
import org.baas.baascore.util.AccountType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByFintechUseNum(String fintechUseNum);
    List<Account> findByCustomerAndIsDeletedAndAccountType(Customer customer, boolean isDeleted, AccountType accountType);

    @EntityGraph(attributePaths = {"customer", "bank"})
    List<Account> findByCustomerId(Long customerId);

}
