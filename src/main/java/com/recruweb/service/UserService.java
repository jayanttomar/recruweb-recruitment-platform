package com.recruweb.service;

import com.recruweb.entity.User;
import com.recruweb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public User registerUser(User user) {

        return userRepository.save(user);
    }

    // Login User
    public Map<String, String> loginUser(User user) {

        User existing = userRepository.findByEmail(user.getEmail());

        Map<String, String> response = new HashMap<>();

        if (existing != null &&
            existing.getPassword().equals(user.getPassword())) {

            response.put("message", "Login Successful");
            response.put("role", existing.getRole());
            response.put("email", existing.getEmail());

            return response;
        }

        response.put("message", "Invalid Credentials");
        return response;
    }
}