package com.example.Propagation.controller;

import com.example.Propagation.service.OuterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/api/propagation", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class PropagationController {

    private final OuterService service;

    //  http://localhost:8080/api/propagation/required?owner=alice
    @PostMapping("/required")
    public ResponseEntity<String> required(@RequestParam String owner) {
        service.callRequired(owner);
        return ResponseEntity.ok("Propagation.REQUIRED finished");
    }

    //  http://localhost:8080/api/propagation/supports?owner=alice
    @PostMapping("/supports")
    public ResponseEntity<String> supports(@RequestParam String owner) {
        service.callSupports(owner);
        return ResponseEntity.ok("Propagation.SUPPORTS finished");
    }


    // http://localhost:8080/api/propagation/mandatory?owner=alice
    @PostMapping("/mandatory")
    public ResponseEntity<String> mandatory(@RequestParam String owner) {
        service.callMandatory(owner);
        return ResponseEntity.ok("Propagation.MANDATORY finished");
    }


    // http://localhost:8080/api/propagation/requires-new?owner=alice
    @PostMapping("/requires-new")
    public ResponseEntity<String> requiresNew(@RequestParam String owner) {
        service.callRequiresNew(owner);
        return ResponseEntity.ok("Propagation.REQUIRES_NEW finished");
    }

    // http://localhost:8080/api/propagation/not-supported?owner=alice
    @PostMapping("/not-supported")
    public ResponseEntity<String> notSupported(@RequestParam String owner) {
        service.callNotSupported(owner);
        return ResponseEntity.ok("Propagation.NOT_SUPPORTED finished");
    }

    // http://localhost:8080/api/propagation/never?owner=alice
    @PostMapping("/never")
    public ResponseEntity<String> never(@RequestParam String owner) {
        service.callNever(owner);
        return ResponseEntity.ok("Propagation.NEVER finished");
    }

    // http://localhost:8080/api/propagation/nested?owner=alice
    @PostMapping("/nested")
    public ResponseEntity<String> nested(@RequestParam String owner) {
        service.callNested(owner);
        return ResponseEntity.ok("Propagation.NESTED finished");
    }
}
