package com.ecomm.project.product_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    @KafkaListener(topics = "order-topic", groupId = "product-service-group")
    public void handleOrderEvent(String message) {
        System.out.println("Received order event in product-service: " + message);
        // process the event
    }
}

