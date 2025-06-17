package com.ecomm.project.product_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<String> getProducts() {
        return ResponseEntity.ok("This is from product service.");
    }
}
