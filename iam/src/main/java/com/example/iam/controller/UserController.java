package com.example.iam.controller;

import com.example.iam.model.UserResponse;
import com.example.iam.model.UserUpdateRequest;
import com.example.iam.service.UserServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceFacade service;

    @GetMapping
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserResponse user = service.get();
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        UserResponse user = service.update(request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    public ResponseEntity<Void> deactivateUser() {
        service.deactivate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
