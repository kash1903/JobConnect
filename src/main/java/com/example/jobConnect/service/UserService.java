package com.example.jobConnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobConnect.model.User;
import com.example.jobConnect.repository.UserRepo;


@Service

public class UserService {


       @Autowired
    private UserRepo userRepo;

    // Register a new user
    public User registerUser(User user) {
        // check if email already exists
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        return userRepo.save(user);
    }

    // Login a user
    public User loginUser(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("Invalid email!");
        }

        if (!user.get().getPassword().equals(password)) {
            throw new RuntimeException("Invalid password!");
        }

        return user.get();
    
}

public User getUserById(Long userId) {
    return userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
}



}
