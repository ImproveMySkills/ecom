package com.improvemyskills.ecom.controllers;

import com.improvemyskills.ecom.services.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(@Qualifier("emailNotificationService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    ResponseEntity<String> processNotification(@RequestParam String to, @RequestParam String message){
        return ResponseEntity.ok(
                notificationService.sendNotification(to, message)
        );

    }

}
