package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.services.NotificationService;
import org.springframework.stereotype.Service;

@Service("pushNotificationService")
public class PushNotificationService implements NotificationService {
    @Override
    public String sendNotification(String to, String message) {
        System.out.println("Push notification sent : "+message);
        return new StringBuilder("Push notification sent : ").append(message).toString();
    }
}
