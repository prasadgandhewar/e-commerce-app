package com.ecomm.project.user_service.controller;

import com.ecomm.project.user_service.models.RegistrationRequest;
import com.ecomm.project.user_service.models.RegistrationResponse;
import com.ecomm.project.user_service.models.ResponseStatus;
import com.ecomm.project.user_service.models.User;
import com.ecomm.project.user_service.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class RegistrationController {

    @Autowired
    RegistrationResponse registerResponse;

    @Autowired
    RegistrationService registerService;

    @PostMapping("/users/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody @Valid RegistrationRequest request) {
        registerService.registerUser(request);
        registerResponse.setStatus(ResponseStatus.SUCCESS);
        registerResponse.setMessage("User registed succesfully");
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }
}
