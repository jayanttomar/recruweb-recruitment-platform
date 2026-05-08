package com.recruweb.service;

import com.recruweb.entity.User;
import com.recruweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public String loginUser(User user) {

        User existing = userRepository.findByEmail(user.getEmail());

        if(existing != null &&
                existing.getPassword().equals(user.getPassword())) {

            return "Login Successful";
        }

        return "Invalid Credentials";
    }
}