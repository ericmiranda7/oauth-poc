package com.poc.auth_server;

import com.poc.auth_server.exception.ConsentNotGrantedException;
import com.poc.auth_server.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    AuthService authService;

    public Controller(AuthService authService) {
        super();
    }

    @GetMapping("/token")
    ResponseEntity<Void> getToken(
            @RequestParam("response_type") String responseType, // will be token for us (implicit grant)
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectUri,
            HttpServletResponse response) throws IOException {

        boolean consented = true; // mocking user giving consent

        if (!consented) {
            throw new ConsentNotGrantedException();
        }

        // consented, continue auth flow
        String token = authService.generateToken();

        // redirect the browser to the client redirectUri with the access token
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Location", redirectUri + "?access_token=" + token
                    + "&token_type=example");
        return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
    }
}
