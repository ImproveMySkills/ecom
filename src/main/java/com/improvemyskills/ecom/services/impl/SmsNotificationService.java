package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.services.NotificationService;
import org.springframework.stereotype.Service;

@Service("smsNotificationService")
public class SmsNotificationService implements NotificationService {
    @Override
    public String sendNotification(String to, String message) {
        System.out.println("SMS sent : "+message);
        return new StringBuilder("SMS sent : ").append(message).toString();
    }
}
