package com.ecomm.project.user_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class UserEventProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishOrderCreatedEvent(String json) {
        kafkaTemplate.send("user-topic", json);
    }
}
