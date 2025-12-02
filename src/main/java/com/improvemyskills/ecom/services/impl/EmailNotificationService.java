package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.services.NotificationService;
import org.springframework.stereotype.Service;

@Service("emailNotificationService")
public class EmailNotificationService implements NotificationService {
    @Override
    public String sendNotification(String to, String message) {
        System.out.println("Email sent : "+message);
        return new StringBuilder("Email sent : ").append(message).toString();
    }
}
