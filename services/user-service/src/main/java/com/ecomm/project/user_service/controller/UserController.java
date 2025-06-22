package com.ecomm.project.user_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@EnableDiscoveryClient
public class UserController {

    @Value("${user.count}")
    String count;

    @GetMapping("/users/list")
    public ResponseEntity<String> getUsers() {


        return new ResponseEntity<>("This is user method and count is - " + count, HttpStatus.OK);
    }
}
