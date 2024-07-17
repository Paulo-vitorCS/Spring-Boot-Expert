package br.com.spring_boot_expert.rest.service.impl;

import br.com.spring_boot_expert.domain.UserCredentials;
import br.com.spring_boot_expert.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserCredentialsRepository userRepository;

    @Transactional
    public UserCredentials save(UserCredentials user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[] {"USER"};

        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

}
