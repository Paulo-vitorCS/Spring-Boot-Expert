package br.com.spring_security.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RhController {

    @GetMapping("/technical")
    @PreAuthorize("hasAnyRole('RH_TECHNICIAN', 'RH_MANAGER', 'ADMIN')")
    public ResponseEntity<String> technical() {
        return ResponseEntity.ok("Technician route");
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('RH_MANAGER', 'ADMIN')")
    public ResponseEntity<String> manager() {
        return ResponseEntity.ok("Manager route");
    }

}
