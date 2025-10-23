package com.example.jobConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LandingController {

    @GetMapping("/")
    public String landingPage() {

        return "index"; // Thymeleaf looks for index.html in templates/
    }

    // Load Registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Handle Registration form submission
    @PostMapping("/register")
    public String handleRegistration(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Model model) {
        // String apiUrl = "http://localhost:8080/api/users/register";
        String apiUrl = "https://jobconnect-2gk2.onrender.com/register";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("role", role);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

            model.addAttribute("message", "Registration Successful 🎉");
        } catch (Exception e) {
            model.addAttribute("message", "Registration Failed ❌");
        }

        // After success, redirect to index page with message
        return "index";
    }

    // Load Login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // new code
    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        // String apiUrl = "http://localhost:8080/api/users/login";
           String apiUrl = "https://jobconnect-2gk2.onrender.com/login";
        RestTemplate restTemplate = new RestTemplate();

        // Prepare request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Call the login API
            Map<String, Object> loginResponse = restTemplate.postForObject(apiUrl, entity, Map.class);
            System.out.println("Login API Response: " + loginResponse);

            // Check for API error
            if (loginResponse == null || loginResponse.containsKey("error")) {
                System.out.println(
                        "Login failed: " + (loginResponse != null ? loginResponse.get("error") : "No response"));
                return "redirect:/login?error";
            }

            // Extract info safely
            Long userId = null;
            Object idObj = loginResponse.get("id");
            if (idObj instanceof Number) {
                userId = ((Number) idObj).longValue();
            } else if (idObj != null) {
                userId = Long.parseLong(idObj.toString());
            }

            String name = (String) loginResponse.get("name");
            String role = (String) loginResponse.get("role");

            System.out.println("Parsed Login Info -> ID: " + userId + ", Name: " + name + ", Role: " + role);

            // Validate extracted info
            if (userId == null || name == null || role == null) {
                System.out.println("Login failed due to missing user info.");
                return "redirect:/login?error";
            }

            // Save in session
            session.setAttribute("userId", userId);
            session.setAttribute("userName", name);
            session.setAttribute("userEmail", email);
            session.setAttribute("userRole", role);

            // Redirect based on exact backend role
            if ("EMPLOYER".equalsIgnoreCase(role)) {
                return "redirect:/employer";
            } else if ("JOB SEEKER".equalsIgnoreCase(role) || "JOB_SEEKER".equalsIgnoreCase(role)) {
                return "redirect:/jobseeker";
            } else {
                System.out.println("Unknown role: " + role);
                return "redirect:/login?error";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login?error";
        }
    }

    // new code

    @GetMapping("/jobseeker")
    public String jobSeekerPage(HttpSession session, Model model) {
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "jobseeker";
    }

    // To logout from jobseeker.html
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clear session
        return "redirect:/login"; // redirect to login page
    }

}
