package br.com.spring_security.api.controllers;

import br.com.spring_security.api.dto.UserRegistrationDTO;
import br.com.spring_security.domain.entity.User;
import br.com.spring_security.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserRegistrationDTO body) {
        User newUser = userService.save(body.getUser(), body.getPermissions());
        return ResponseEntity.ok(newUser);
    }

}
