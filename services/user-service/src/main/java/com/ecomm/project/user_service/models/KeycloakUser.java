package com.ecomm.project.user_service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeycloakUser {
    private String id;
    private String username;
    private String email;
}
