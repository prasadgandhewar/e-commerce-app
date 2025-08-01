package com.ecomm.project.user_service.service;

import com.ecomm.project.user_service.logging.Loggable;
import com.ecomm.project.user_service.models.KeycloakUser;
import com.ecomm.project.user_service.models.LoginRequest;
import com.ecomm.project.user_service.models.RegistrationRequest;
import com.ecomm.project.user_service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Loggable
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Autowired
    private RestTemplate restTemplate;

    public String getAdminAccessToken() {
        String tokenUrl = keycloakUrl + "/realms/master/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "admin-cli");
        params.add("username", "admin");
        params.add("password", "admin");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody() != null ? response.getBody().get("access_token").toString() : null;
        } else {
            throw new RuntimeException("Failed to obtain Keycloak admin token");
        }
    }

    // password, client-credentials, AuthorizationCode, Implicit, DeviceCode, RefreshToken
    public String getUserAccessToken(LoginRequest requestDto) {
        String tokenUrl = keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("username", requestDto.getUsername());
        params.add("password", requestDto.getPassword());
        params.add("client_secret", clientSecret);
        params.add("scope", "openid profile email");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().get("access_token").toString();
        } else {
            throw new RuntimeException("Failed to login");
        }
    }

    public Object getUserInfo(LoginRequest requestDto) {
        String userToken = getUserAccessToken(requestDto);
        System.out.println("Usertoken - " + userToken);
        String getUserUrl = keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(userToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(
                getUserUrl,
                HttpMethod.GET,
                entity,
                Object.class);
        return response.getBody();

    }

    public Boolean checkIfUserExists(RegistrationRequest requestDto, String token) {
        String getUserUrl = keycloakUrl + "/admin/realms/" + realm + "/users?email=" + requestDto.getEmail();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<KeycloakUser[]> response = restTemplate.exchange(
                getUserUrl,
                HttpMethod.GET,
                entity,
                KeycloakUser[].class);
        return response.getBody() != null && response.getBody().length > 0;
    }

    public String registerUserInKeycloak(RegistrationRequest requestDto) {
        String token = getAdminAccessToken();
        Boolean userExists = checkIfUserExists(requestDto, token);
        if (userExists) {
            return Constants.USER_EXISTS;
        }
        String createUserUrl = keycloakUrl + "/admin/realms/" + realm + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> user = new HashMap<>();
        user.put("username", requestDto.getEmail());
        user.put("email", requestDto.getEmail());
        user.put("enabled", true);
        user.put("emailVerified", true);
//        user.put("firstName", "Prasad");
//        user.put("lastName", "Gbd");
        //user.put("requiredActions", Collections.emptyList());
        user.put("credentials", List.of(Map.of(
                "type", "password",
                "value", requestDto.getPassword(),
                "temporary", false
        )));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(user, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(createUserUrl, entity, Void.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            // Extract user ID from Location header
            String location = Objects.requireNonNull(response.getHeaders().getLocation()).toString();
            return location.substring(location.lastIndexOf("/") + 1);
        } else {
            throw new RuntimeException("Failed to create user in Keycloak. Status: " + response.getStatusCode());
        }
    }
}

