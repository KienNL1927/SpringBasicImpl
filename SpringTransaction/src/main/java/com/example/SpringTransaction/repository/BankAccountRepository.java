package com.example.SpringTransaction.repository;

import com.example.SpringTransaction.entity.BankAccount;

import java.util.Optional;

public class BankAccountRepository {
    public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
        Optional<BankAccount> findByName(String name);
    }

}
