package com.example.iam.controller;

import com.example.iam.model.AuthResponse;
import com.example.iam.model.LoginRequest;
import com.example.iam.model.RegistrationRequest;
import com.example.iam.service.UserServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceFacade service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse authResponse = service.login(request);
        return ResponseEntity.ok(authResponse);
    }
}
