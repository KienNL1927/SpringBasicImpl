package com.example.services;

import org.springframework.stereotype.Component;

@Component
public class SimpleService {
    public String greet() {
        return "Hello from MyService!";
    }
}
