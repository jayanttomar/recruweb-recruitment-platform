package com.recruweb.controller;

import com.recruweb.entity.User;
import com.recruweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

  
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        Map<String, String> response = userService.loginUser(user);

        return response;
    }
}