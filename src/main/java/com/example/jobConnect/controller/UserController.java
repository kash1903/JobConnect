package com.example.jobConnect.controller;

import com.example.jobConnect.model.User;
import com.example.jobConnect.service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")  
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER endpoint
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return "User registered successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

  
// New LOGIN END POINTS 
@PostMapping("/login")
public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {
    Map<String, Object> response = new HashMap<>();
    try {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());

        response.put("id", loggedInUser.getId());
        response.put("name", loggedInUser.getName());
        response.put("role", loggedInUser.getRole());
        response.put("message", "Welcome, " + loggedInUser.getName() + "!");

        return ResponseEntity.ok(response);  // ✅ Success = HTTP 200

    } catch (Exception e) {
        response.put("error", "Invalid email or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // ❌ Fail = HTTP 401
    }
}




// We need this so we know if a logged-in user is an employer or seeker.
    @GetMapping("/{userId}")
    public User getUserInfo(@PathVariable Long userId) {
    return userService.getUserById(userId);
}

}
