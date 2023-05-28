package com.springsecuirty2023.controller;

import com.springsecuirty2023.entities.dto.UserDto;
import com.springsecuirty2023.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUserForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, UserDto userDto) {
        if (userService.registerUser(userDto)) {
            model.addAttribute("message", "User successfully register!");
        }
        model.addAttribute("message", "failed to register user!");
        return "redirect:/login";
    }
}
