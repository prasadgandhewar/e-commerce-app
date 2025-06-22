package com.ecomm.project.product_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ProductController {
    @Value("${user.count}")
    String count;

    @GetMapping("/products/list")
    public ResponseEntity<String> getProducts() {
        return ResponseEntity.ok("This is from product service- " + count);
    }
}
