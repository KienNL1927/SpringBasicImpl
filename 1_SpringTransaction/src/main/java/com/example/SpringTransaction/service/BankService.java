package com.example.SpringTransaction.service;

import com.example.SpringTransaction.entity.BankAccount;
import com.example.SpringTransaction.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

@Service
@AllArgsConstructor
public class BankService {

    @Autowired
    private BankAccountRepository repo;

    // Isolation: READ_COMMITTED
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommitted(String name) {
        BankAccount acc = repo.findByName(name).orElseThrow();
        System.out.println("READ_COMMITTED: Before sleep - balance = " + acc.getBalance());
        sleep(10000); // simulate delay
        BankAccount after = repo.findByName(name).orElseThrow();
        System.out.println("READ_COMMITTED: After sleep - balance = " + after.getBalance());
    }

    // Isolation: REPEATABLE_READ
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableRead(String name) {
        BankAccount acc = repo.findByName(name).orElseThrow();
        System.out.println("REPEATABLE_READ: First read = " + acc.getBalance());
        sleep(10000);
        BankAccount again = repo.findByName(name).orElseThrow();
        System.out.println("REPEATABLE_READ: Second read = " + again.getBalance());
    }

    // Isolation: SERIALIZABLE
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializableUpdate(String name, int amount) {
        BankAccount acc = repo.findByName(name).orElseThrow();
        acc.setBalance(acc.getBalance() + amount);
        System.out.println("SERIALIZABLE: Updating balance to " + acc.getBalance());
        repo.save(acc);
        sleep(10000); // Hold transaction
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
