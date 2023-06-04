package com.springsecuirty2023.controller;

import com.springsecuirty2023.entities.User;
import com.springsecuirty2023.entities.dto.UserDto;
import com.springsecuirty2023.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ss023/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getString")
    public ResponseEntity<String> getString(){
        return new ResponseEntity<>("String", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        //return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.OK);
        return null;
    }
}
