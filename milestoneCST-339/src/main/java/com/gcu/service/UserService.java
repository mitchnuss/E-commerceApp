package com.gcu.service;

import com.gcu.model.UserModel;
import com.gcu.repository.UserRepositoryInterface;


import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositoryInterface userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepositoryInterface userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel registerUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public UserModel getUserByUsername(String username) {
        Optional<UserModel> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new IllegalArgumentException("User not found: " + username);
        }
    }
}
