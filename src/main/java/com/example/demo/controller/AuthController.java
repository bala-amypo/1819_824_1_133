package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService obj;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return obj.register(user);
    }

    @PostMapping("/login")
    public User login(
            @RequestParam String email,
            @RequestParam String password) {

        return obj.login(email, password);
    }
}
