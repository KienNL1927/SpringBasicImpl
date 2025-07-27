package com.example.Propagation.service;

import com.example.Propagation.entity.Account;
import com.example.Propagation.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InnerService {

    private final AccountRepository repo;

    @Transactional(propagation = Propagation.REQUIRED)
    public void required(String owner) {
        repo.save(new Account(null, owner + ":REQUIRED", 100));
        throwIf(owner);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports(String owner) {
        repo.save(new Account(null, owner + ":SUPPORTS", 100));
        throwIf(owner);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory(String owner) {
        repo.save(new Account(null, owner + ":MANDATORY", 100));
        throwIf(owner);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew(String owner) {
        repo.save(new Account(null, owner + ":REQUIRES_NEW", 100));
        throwIf(owner);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported(String owner) {
        repo.save(new Account(null, owner + ":NOT_SUPPORTED", 100));
        throwIf(owner);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void never(String owner) {
        repo.save(new Account(null, owner + ":NEVER", 100));
        throwIf(owner);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested(String owner) {
        repo.save(new Account(null, owner + ":NESTED", 100));
        throwIf(owner);
    }

    private void throwIf(String owner) {
        if (owner.contains("fail")) {
            throw new RuntimeException("For testing rollback behaviour");
        }
    }
}
