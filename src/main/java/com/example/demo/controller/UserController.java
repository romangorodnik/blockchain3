package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        try {
            userService.registerUser(user.getUsername(), user.getPassword());
            return "redirect:/";
        } catch (NoSuchAlgorithmException e) {
            return "error";
        }
    }
}
