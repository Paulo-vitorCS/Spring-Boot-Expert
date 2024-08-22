package br.com.spring_security.api.config;

import br.com.spring_security.domain.security.CustomAuthentication;
import br.com.spring_security.domain.security.UserIdentification;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
            UserIdentification userIdentification = new UserIdentification(
                    "I am Master",
                    "Master",
                    masterLogin,
                    List.of("ADMIN"));

            return new CustomAuthentication(userIdentification);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
