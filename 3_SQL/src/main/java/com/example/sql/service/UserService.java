package com.example.sql.service;

import com.example.sql.entity.User;
import com.example.sql.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void demoQueries() {
        // Save some data
        repo.save(new User("Alice", "alice@mail.com", "ACTIVE"));
        repo.save(new User("Bob", "bob@mail.com", "INACTIVE"));

        // Using @Query with JPQL
        User user = repo.findByEmail("alice@mail.com");
        System.out.println("Find by email -> " + user.getName());

        // Using named parameter
        List<User> activeUsers = repo.findByStatus("ACTIVE");
        System.out.println("Active users count = " + activeUsers.size());

        // Using native query
        List<User> nativeResult = repo.findNativeByName("Alice");
        System.out.println("Native query found " + nativeResult.size() + " user(s).");
    }
}
