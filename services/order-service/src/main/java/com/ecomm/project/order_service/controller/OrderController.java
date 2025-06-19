package com.ecomm.project.order_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/orders/list")
    public ResponseEntity<String> getOrders() {
        return ResponseEntity.ok("This is from order service.");
    }
}
