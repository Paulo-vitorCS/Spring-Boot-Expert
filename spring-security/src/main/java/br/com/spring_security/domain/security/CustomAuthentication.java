package br.com.spring_security.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public class CustomAuthentication implements Authentication {

    private final UserIdentification userIdentification;

    public CustomAuthentication(UserIdentification userIdentification) {
        if (userIdentification == null) {
            throw new ExceptionInInitializerError(
                    "It is not possible to create a custom authentication without user identification"
            );
        }
        this.userIdentification = userIdentification;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userIdentification
                .getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission))
                .toList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.userIdentification;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Alredy authenticated");
    }

    @Override
    public String getName() {
        return this.userIdentification.getName();
    }
}
