package br.com.spring_boot_expert.rest.controller;

import br.com.spring_boot_expert.domain.UserCredentials;
import br.com.spring_boot_expert.exceptions.InvalidPasswordException;
import br.com.spring_boot_expert.rest.dto.CredentialsDTO;
import br.com.spring_boot_expert.rest.dto.TokenDTO;
import br.com.spring_boot_expert.rest.service.impl.UserServiceImpl;
import br.com.spring_boot_expert.security.jwt.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCredentialsController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCredentials save(@RequestBody @Valid UserCredentials user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userService.save(user);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {
        try {
            UserCredentials user = UserCredentials.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword())
                    .build();
            UserDetails authUser = userService.authenticate(user);

            String token = jwtService.generateToken(user);

            return new TokenDTO(user.getLogin(), token);
        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
