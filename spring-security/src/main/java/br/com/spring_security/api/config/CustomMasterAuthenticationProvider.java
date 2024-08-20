package br.com.spring_security.api.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomMasterAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        String masterLogin  = "master";
        String masterPassword = "@321";

        if (masterLogin.equals(login) && masterPassword.equals(password)) {
            return new UsernamePasswordAuthenticationToken("I'm Master", null,
                    List.of(new SimpleGrantedAuthority("ADMIN")));
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
