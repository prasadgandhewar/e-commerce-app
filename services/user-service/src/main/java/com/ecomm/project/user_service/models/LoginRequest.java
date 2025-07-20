package com.ecomm.project.user_service.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class LoginRequest {
    private String password;
    private String username;
}
