package com.waterwatch.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.waterwatch.backend.model.User;
import com.waterwatch.backend.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
