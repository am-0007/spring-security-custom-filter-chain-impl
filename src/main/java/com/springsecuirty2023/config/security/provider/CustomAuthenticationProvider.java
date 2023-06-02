package com.springsecuirty2023.config.security.provider;

import com.springsecuirty2023.config.security.authentication.CustomAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        String username = (String) customAuthentication.getPrincipal();
        String password = customAuthentication.getCredential();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails.getUsername().equals(username)
                && passwordEncoder.matches(password, userDetails.getPassword())) {
            return new CustomAuthentication(username, password, true);
        }
        throw new BadCredentialsException("Username or password incorrect!!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
