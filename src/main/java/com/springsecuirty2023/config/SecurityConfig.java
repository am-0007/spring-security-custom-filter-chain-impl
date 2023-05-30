package com.springsecuirty2023.config;

//import com.springsecuirty2023.config.security.filters.CustomAuthenticationFilter;
//import com.springsecuirty2023.config.security.filters.CustomAuthentication2Filter;
//import com.springsecuirty2023.config.security.filters.CustomAuthenticationFilter;
//import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
//import com.springsecuirty2023.config.security.filters.CustomAuthentication2Filter;
import com.springsecuirty2023.config.security.filters.CustomAuthenticationFilter;
import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                //.exceptionHandling()
                //Add filter where username and password is checked
                //.authenticationEntryPoint(authenticationEntryPoint)
                //.and()
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers("/ss023/user/register",
                                                "/css/**",
                                                "/register"
                                        )
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
//                .formLogin(Customizer.withDefaults())
                .formLogin(
                        form -> form.loginPage("/login")
                                .permitAll()
                )
                .addFilterBefore(new CustomAuthenticationFilter(customAuthenticationManager), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAt(new CustomAuthentication2Filter(), CustomAuthenticationFilter.class)
                .build();
    }



}
