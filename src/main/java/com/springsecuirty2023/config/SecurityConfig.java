package com.springsecuirty2023.config;

import com.springsecuirty2023.config.security.filters.CustomUsernamePasswordAuthenticationFilter;
import com.springsecuirty2023.config.security.filters.JwtAuthenticationFilter;
import com.springsecuirty2023.config.security.jwt.JwtTokenService;
import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers(
                                                "/ss023/user/register",
                                                "/ss023/user/getStringTwo",
                                                "/css/**",
                                                "/register",
                                                "/login"
                                        )
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .addFilterAt(
                        new CustomUsernamePasswordAuthenticationFilter(customAuthenticationManager, new JwtTokenService()),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterAfter(
                        new JwtAuthenticationFilter(new JwtTokenService(), userDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                        /*CustomUsernamePasswordAuthenticationFilter.class*/
                )
                .build();
    }
}
