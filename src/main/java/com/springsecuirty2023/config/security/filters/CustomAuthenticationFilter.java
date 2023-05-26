package com.springsecuirty2023.config.security.filters;

import com.springsecuirty2023.config.security.authentication.CustomAuthentication;
import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. create an authentication object if not authenticated
        // 2. delegate the authentication object to the manager
        // 3. get authentication from manager as a response
        // 4. Once authenticated then send request to the next filter chain

        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication customAuthentication = new CustomAuthentication(key, false);
        var userAuthentication = customAuthenticationManager.authenticate(customAuthentication);

        if (userAuthentication.isAuthenticated()) {

            // for authorization purposes in later phases or future
            SecurityContextHolder.getContext().setAuthentication(userAuthentication);
            filterChain.doFilter(request, response); //only when authentication work
        }

    }
}
