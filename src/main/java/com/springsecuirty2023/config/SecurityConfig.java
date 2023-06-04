package com.springsecuirty2023.config;

//import com.springsecuirty2023.config.security.filters.CustomAuthenticationFilter;
//import com.springsecuirty2023.config.security.filters.CustomAuthentication2Filter;
//import com.springsecuirty2023.config.security.filters.CustomAuthenticationFilter;
//import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
//import com.springsecuirty2023.config.security.filters.CustomAuthentication2Filter;
import com.springsecuirty2023.config.security.filters.CustomUsernamePasswordAuthenticationFilter;
import com.springsecuirty2023.config.security.filters.JwtAuthenticationFilter;
import com.springsecuirty2023.config.security.jwt.JwtTokenService;
import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                //.exceptionHandling()
                //Add filter where username and password is checked
                //.authenticationEntryPoint(authenticationEntryPoint)
                //.and()
                .addFilter(new CustomUsernamePasswordAuthenticationFilter(customAuthenticationManager, new JwtTokenService()))
                .addFilterAfter(new JwtAuthenticationFilter(new JwtTokenService(), userDetailsService), CustomUsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers("/ss023/user/register",
                                                "/css/**",
                                                "/register",
                                                "/login"
                                        )
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .formLogin(Customizer.withDefaults())
                /*.formLogin(
                        form -> form.loginPage("/login")
                                .permitAll()
                )*/
                //.addFilterAfter(new CustomAuthenticationFilter(customAuthenticationManager), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAt(new CustomAuthentication2Filter(), CustomAuthenticationFilter.class)
                .build();
    }



}
