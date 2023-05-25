package com.springsecuirty2023.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers("/ss023/user/register")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }


}
