package com.example.jobConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployerController {
     @GetMapping("/employer")
    public String employerDashboard(HttpSession session, Model model) {
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "employer"; // points to employer.html
    }
}
