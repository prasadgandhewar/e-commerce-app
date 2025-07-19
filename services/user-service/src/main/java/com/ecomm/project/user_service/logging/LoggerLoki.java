package com.ecomm.project.user_service.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoggerLoki {

    @Autowired
    RestTemplate restTemplate;
    @Value("${logger.loki.url}")
    String url;

    public void log(String message) {

        Map<String, Object> stream = new HashMap<>();
        stream.put("job", "e-commerce-app");

        long timestamp = System.currentTimeMillis() * 1_000_000L;

        List<Object> logEntry = Arrays.asList(String.valueOf(timestamp), message);

        Map<String, Object> streamEntry = new HashMap<>();
        streamEntry.put("stream", stream);
        streamEntry.put("values", List.of(logEntry));

        Map<String, Object> payload = new HashMap<>();
        payload.put("streams", List.of(streamEntry));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println(response.getStatusCode());

    }
}
