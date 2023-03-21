package com.example.iam.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String subject;
    private final List<String> roles;

    public JwtAuthenticationToken(String subject, List<String> roles) {
        super(null);
        this.subject = subject;
        this.roles = roles;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return subject;
    }

    public List<String> getRoles() {
        return roles;
    }
}
