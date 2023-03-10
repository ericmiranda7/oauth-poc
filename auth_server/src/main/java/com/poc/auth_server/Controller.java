package com.poc.auth_server;

import com.poc.auth_server.exception.ConsentNotGrantedException;
import com.poc.auth_server.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@org.springframework.stereotype.Controller
public class Controller {

    AuthService authService;

    public Controller(AuthService authService) {
        super();
        this.authService = authService;
    }

    // todo(): create a registration endpoint ?

    @GetMapping("/authorize")
    @CrossOrigin
    String authorize(@RequestParam("response_type") String responseType,
                           @RequestParam("client_id") String clientId,
                           @RequestParam("redirect_uri") String redirectUri,
                           ModelMap model) {
        model.addAttribute("response_type", responseType);
        model.addAttribute("client_id", clientId);
        model.addAttribute("redirect_uri", redirectUri);
        return "login.html";
    }


    @PostMapping("/token")
    @ResponseBody
    ResponseEntity<Void> getToken(@RequestBody String rb) {
        int startInd = rb.indexOf("redirectUri=");
        String redirectUri = URLDecoder.decode(rb.substring(startInd + 12, rb.indexOf("clientId") - 1), StandardCharsets.UTF_8);
        boolean consented = true; // mocking user giving consent

        if (!consented) {
            throw new ConsentNotGrantedException();
        }

        // consented, continue auth flow
        String token = "#access_token=" + authService.generateToken();

        // redirect the browser to the client redirectUri with the access token
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUri + token)).build();
    }
}
