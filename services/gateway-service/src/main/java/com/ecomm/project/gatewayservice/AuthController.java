package com.ecomm.project.gatewayservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/token")
    public Mono<ResponseEntity<String>> getToken() {
        return authService.getToken()
                .map(token -> ResponseEntity.ok("Bearer " + token))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error: " + e.getMessage())));
    }

    @GetMapping("/getStatus")
    public ResponseEntity<String> getStatusTest() {
        return new ResponseEntity<>("Working!!", HttpStatus.OK);
    }
}
