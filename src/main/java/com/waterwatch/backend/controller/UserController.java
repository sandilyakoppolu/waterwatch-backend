package com.waterwatch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waterwatch.backend.model.User;
import com.waterwatch.backend.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    // REGISTER
    @PostMapping("/register")
    public Object registerUser(@RequestBody User user) {

        Optional<User> existingUser = service.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        User savedUser = service.registerUser(user);

        savedUser.setPassword(null); // hide password
        return savedUser;
    }

    // LOGIN
    @PostMapping("/login")
    public Object loginUser(@RequestBody User user) {

        Optional<User> existingUser = service.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            return "User not found!";
        }

        User dbUser = existingUser.get();

        if (!service.checkPassword(user.getPassword(), dbUser.getPassword())) {
            return "Invalid password!";
        }

        dbUser.setPassword(null); // hide password
        return dbUser;
    }
}
