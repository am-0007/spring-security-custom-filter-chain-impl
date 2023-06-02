package com.springsecuirty2023.config.security.provider;

import com.springsecuirty2023.config.security.authentication.CustomAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken customAuthentication = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) customAuthentication.getPrincipal();
        String password = (String) customAuthentication.getCredentials();

        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        System.out.println(authorities);

        if (userDetails.getUsername().equals(username)
                && passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        throw new BadCredentialsException("Username or password incorrect!!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
