package com.ecomm.project.user_service.controller;

import com.ecomm.project.user_service.models.LoginRequest;
import com.ecomm.project.user_service.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/users/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request) {

        return new ResponseEntity<>(loginService.login(request), HttpStatus.OK);
    }
}
