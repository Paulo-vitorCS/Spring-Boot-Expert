package br.com.spring_security.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Public route ok!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication) {
        return ResponseEntity.ok("Private route ok! User: " + authentication.getName());
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminRoute(Authentication authentication) {
        return ResponseEntity.ok("Admin route ok!");
    }

}
