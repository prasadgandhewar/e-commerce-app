package com.ecomm.project.user_service.controller;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
public class UserController {

    @GetMapping("/users/list")
    public ResponseEntity<String> getUsers() {
        return new ResponseEntity<>("This is user method", HttpStatus.OK);
    }
}
