package com.example.jobConnect.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;


@Service
public class TwilioService {


      @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.messaging.service.sid}")
    private String messagingServiceSid;

    // Method to send SMS
    public void sendSms(String to, String messageBody) {
        try {
            Twilio.init(accountSid, authToken);

            Message.creator(
                    new PhoneNumber(to),    // recipient
                    messagingServiceSid,    // Messaging Service SID
                    messageBody
            ).create();

            System.out.println("SMS sent successfully to " + to);

        } catch (Exception e) {
            System.out.println("Error sending SMS to " + to + ": " + e.getMessage());
        }

    
}
}
