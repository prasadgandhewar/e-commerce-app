package com.ecomm.project.user_service.service;

import com.ecomm.project.user_service.exceptions.UserAlreadyExistsException;
import com.ecomm.project.user_service.logging.Loggable;
import com.ecomm.project.user_service.models.RegistrationRequest;
import com.ecomm.project.user_service.models.User;
import com.ecomm.project.user_service.repository.UserRepository;
import com.ecomm.project.user_service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Loggable
public class RegistrationService {

    @Autowired
    KeycloakService keycloakService;

    @Autowired
    UserRepository userRepository;

    public void registerUser(RegistrationRequest request) {
        request.setUsername(request.getEmail());
        // Step 1: Register in Keycloak
        String keycloakUserId = keycloakService.registerUserInKeycloak(request);
        if (keycloakUserId.equals(Constants.USER_EXISTS)) {
            throw new UserAlreadyExistsException("Email already exists. Please use different email");
        }
        User user = new User();
        // Step 2: Save extra data in PostgreSQL
        user.setKeycloakUserId(keycloakUserId);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userRepository.save(user);
    }
}
