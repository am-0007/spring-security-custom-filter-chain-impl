package com.springsecuirty2023.config.security.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Data
@RequiredArgsConstructor
public class CustomAuthentication implements Authentication {

    private String principal;
    private String Credential;
    private Collection<GrantedAuthority> authorities;
    private Boolean authenticated = false;

    public CustomAuthentication(String principal,
                                String credential,
                                Collection<GrantedAuthority> authorities) {
        this.principal = principal;
        Credential = credential;
        this.authorities = authorities;
    }

    public CustomAuthentication(String principal, String credential) {
        this.principal = principal;
        Credential = credential;
    }

    public CustomAuthentication(String principal,
                                String credential,
                                Boolean authenticated) {
        this.principal = principal;
        Credential = credential;
        this.authenticated = authenticated;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return getCredential();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public String getName() {
        return null;
    }
}
