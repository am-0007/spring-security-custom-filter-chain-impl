package com.springsecuirty2023.controller;

import com.springsecuirty2023.entities.login.Login;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginUser(Model model, Login login) {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to home page");
        return "home";
    }
}
