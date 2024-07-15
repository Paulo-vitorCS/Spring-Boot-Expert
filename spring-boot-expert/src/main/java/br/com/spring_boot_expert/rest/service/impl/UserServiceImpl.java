package br.com.spring_boot_expert.rest.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("teste")) {
            throw new UsernameNotFoundException("User not found.");
        }

        return User.builder()
                .username("teste")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER", "ADMIN")
                .build();
    }

}
