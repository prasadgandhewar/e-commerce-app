package com.ecomm.project.user_service.controller;

import com.ecomm.project.user_service.models.User;
import com.ecomm.project.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@EnableDiscoveryClient
public class UserController {

    @Value("${user.count}")
    String count;

    @Autowired
    UserService userService;

    @GetMapping("/users/list")
    public ResponseEntity<String> getUsers() {
        return new ResponseEntity<>("This is user method and count is - " + count, HttpStatus.OK);
    }

    @GetMapping("/users/getUsername")
    public ResponseEntity<String> getUsername() {
        return new ResponseEntity<>("The usename is - " + userService.getUsername(1), HttpStatus.OK);
    }

    @GetMapping("/users/getAllUsers")
    public ResponseEntity<List<User>> getALlUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
