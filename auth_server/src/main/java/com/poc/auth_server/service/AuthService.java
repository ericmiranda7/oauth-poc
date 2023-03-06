package com.poc.auth_server.service;

import com.poc.auth_server.model.TokenResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public static boolean verifyAuthCode() {
        return true;
    }

    public String generateToken() {
        return "AgBes2t";
    }
}
