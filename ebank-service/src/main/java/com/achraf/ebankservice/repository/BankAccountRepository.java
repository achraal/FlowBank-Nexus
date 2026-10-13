package com.achraf.ebankservice.repository;

import com.achraf.ebankservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomerId(String customerId);
}
