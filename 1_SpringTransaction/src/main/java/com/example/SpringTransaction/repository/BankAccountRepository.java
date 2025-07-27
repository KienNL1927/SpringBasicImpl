package com.example.SpringTransaction.repository;

import com.example.SpringTransaction.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
        Optional<BankAccount> findByName(String name);
    }

