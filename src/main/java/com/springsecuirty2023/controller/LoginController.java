package com.springsecuirty2023.controller;

import com.springsecuirty2023.config.security.authentication.CustomAuthentication;
import com.springsecuirty2023.config.security.manager.CustomAuthenticationManager;
import com.springsecuirty2023.entities.login.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*@Controller*/
@RestController
@RequestMapping("/ss023/user")
@RequiredArgsConstructor
public class LoginController {

    private final CustomAuthenticationManager customAuthenticationManager;

    /*@GetMapping("/login")
    public String loginUser(Model model*//*, Login login*//*) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, Login login) {
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to home page");
        return "home";
    }*/

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Authentication authentication = customAuthenticationManager.authenticate(
                new CustomAuthentication(
                        login.getUsername(),
                        login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }*/
}
