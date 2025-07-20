package com.example.SpringTransaction.entity;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer balance;

    // Getters/setters
}
