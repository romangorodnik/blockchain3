package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Validated UserRequest userRequest) {
        try {
            userService.registerUser(userRequest.getUsername(), userRequest.getPassword());
            return "success";
        } catch (NoSuchAlgorithmException e) {
            return "error";
        }
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            boolean isValid = userService.verifyPassword(username, password);
            if (isValid) {
                return "success";
            } else {
                return "failure";
            }
        } catch (NoSuchAlgorithmException e) {
            return "error";
        }
    }
}
