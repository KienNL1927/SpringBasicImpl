package com.example.sql;

import com.example.sql.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class SqlApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SqlApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userService.demoQueries();
    }
}

/*
Output:
    Find by email -> Alice
    Active users count = 1
    Native query found 1 user(s).

 */
