package com.ecomm.project.user_service.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    @KafkaListener(topics = "order-topic", groupId = "user-service-group")
    public void handleOrderEvent(String message) {
        System.out.println("Received order event in user-service: " + message);
        // process the event
    }
}

