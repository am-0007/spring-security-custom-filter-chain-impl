package com.springsecuirty2023.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecuirty2023.config.security.authentication.CustomAuthentication;
import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
import com.springsecuirty2023.entities.login.Login;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

/*
@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;
*/


    /*@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. create an authentication object if not authenticated
        // 2. delegate the authentication object to the manager
        // 3. get authentication from manager as a response
        // 4. Once authenticated then send request to the next filter chain
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String username = String.valueOf(request.getHeader("username"));
        String password = String.valueOf(request.getHeader("password"));

        if (username.equals("null") || password.equals("null")) {
            httpResponse.sendRedirect("/login");
            return;
//            filterChain.doFilter(request, response);
        }

        if (username != null && password != null) {
            CustomAuthentication customAuthentication = new CustomAuthentication(username, password);
            var userAuthentication = customAuthenticationManager.authenticate(customAuthentication);

            if (userAuthentication.isAuthenticated()) {

                // for authorization purposes in later phases or future
                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                filterChain.doFilter(request, response); //only when authentication work
            }
        } else {
            response.sendRedirect("/login");
        }


    }*/

/*
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Retrieve the username and password from the headers
        String username = request.getHeader("username");
        String password = request.getHeader("password");

        */
/*String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // Deserialize the request body to a Login object using a JSON library (e.g., Jackson)
        ObjectMapper objectMapper = new ObjectMapper();
        Login login = objectMapper.readValue(requestBody, Login.class);

        String username = login.getUsername();
        String password = login.getPassword();*//*



        */
/*String username = null;
        String password = null;*//*


        if ( username == null ||
                password == null) {
            redirectToLoginPage(request, response);
            return;
        }

        CustomAuthentication customAuthentication = new CustomAuthentication(username, password);
        try {
            // Authenticate the user using the authentication manager
            Authentication userAuthentication = customAuthenticationManager.authenticate(customAuthentication);

            if (userAuthentication.isAuthenticated()) {
                // Authentication successful
                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                filterChain.doFilter(request, response);
            } else {
                // Authentication failed
                redirectToLoginPage(request, response);
            }
        } catch (AuthenticationException e) {
            // Authentication exception occurred
            redirectToLoginPage(request, response);
        }
    }

    */
/*private void redirectToLoginPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }*//*

    private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loginUrl = request.getContextPath() + "/login";
        System.out.println(loginUrl);
        response.sendRedirect(loginUrl);
    }


}
*/