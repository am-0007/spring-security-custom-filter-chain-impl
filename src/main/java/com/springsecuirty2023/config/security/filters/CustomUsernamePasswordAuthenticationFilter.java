package com.springsecuirty2023.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecuirty2023.config.security.jwt.JwtProperties;
import com.springsecuirty2023.config.security.jwt.JwtTokenService;
import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
import com.springsecuirty2023.entities.login.Login;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    /*public CustomUsernamePasswordAuthenticationFilter(CustomAuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }*/

    public CustomUsernamePasswordAuthenticationFilter(CustomAuthenticationManager authenticationManager1, JwtTokenService jwtTokenService) {
        super(authenticationManager1);
        this.authenticationManager = authenticationManager1;
        this.jwtTokenService = jwtTokenService;
    }


/*    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }*/


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Login loginUser;
        try {
            loginUser = new ObjectMapper().readValue(request.getInputStream(), Login.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(),
                loginUser.getPassword(),
                new ArrayList<>()
        );

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String principal = (String) authResult.getPrincipal();
        String token = null;

        // Create Jwt token
        try {
            token = jwtTokenService.generateToken(principal);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

    }
}
