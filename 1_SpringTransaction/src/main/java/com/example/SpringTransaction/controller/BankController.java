package com.example.SpringTransaction.controller;

import com.example.SpringTransaction.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/v1/api/bank", produces = {MediaType.APPLICATION_JSON_VALUE})

public class BankController {

    @Autowired
    private BankService service;

    @GetMapping("/read-committed")
    public ResponseEntity<String> testReadCommitted(@RequestParam String name) {
        service.readCommitted(name);
        return ResponseEntity.ok("✅ Read Committed transaction executed for: " + name);
    }
    /*
    GET http://localhost:8080/bank/read-committed?name=Alice
     */

    @GetMapping("/repeatable-read")
    public ResponseEntity<String> testRepeatableRead(@RequestParam String name) {
        service.repeatableRead(name);
        return ResponseEntity.ok("✅ Repeatable Read transaction executed for: " + name);
    }
    /*
    GET http://localhost:8080/bank/repeatable-read?name=Alice
    POST http://localhost:8080/bank/serializable?name=Alice&amount=500
     */

    @PostMapping("/serializable")
    public ResponseEntity<String> testSerializable(@RequestParam String name, @RequestParam int amount) {
        service.serializableUpdate(name, amount);
        return ResponseEntity.ok("✅ Serializable transaction executed: added " + amount + " to " + name);
    }
    /*
    POST http://localhost:8080/bank/serializable?name=Alice&amount=500
     */
}
