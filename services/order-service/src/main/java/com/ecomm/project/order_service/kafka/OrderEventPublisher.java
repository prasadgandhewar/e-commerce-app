package com.ecomm.project.order_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${order.topic.name:order-topic}")
    private String topicName;


    public void publishOrderEvent(String orderJson) {
        kafkaTemplate.send(topicName, orderJson);
    }
}
