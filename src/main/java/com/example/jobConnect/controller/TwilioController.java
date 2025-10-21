package com.example.jobConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobConnect.service.TwilioService;

@RestController
@RequestMapping("/api/notifications")
public class TwilioController {
    
      @Autowired
    private TwilioService twilioService;

    @PostMapping("/send")
    public String sendTestSms(@RequestParam String to, @RequestParam String message) {
        twilioService.sendSms(to, message);
        return "SMS sent (check logs for errors)";
    }
}
