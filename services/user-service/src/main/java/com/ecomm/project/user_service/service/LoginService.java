package com.ecomm.project.user_service.service;

import com.ecomm.project.user_service.logging.Loggable;
import com.ecomm.project.user_service.models.LoginRequest;
import com.ecomm.project.user_service.models.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Loggable
public class LoginService {

    @Autowired
    KeycloakService keycloakService;

    public Object login(LoginRequest request) {

        return keycloakService.getUserInfo(request);
    }
}
