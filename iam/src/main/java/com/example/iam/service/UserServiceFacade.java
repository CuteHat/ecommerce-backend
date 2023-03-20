package com.example.iam.service;

import com.example.iam.model.AuthResponse;
import com.example.iam.model.LoginRequest;
import com.example.iam.model.RegistrationRequest;

public interface UserServiceFacade {
    AuthResponse register(RegistrationRequest request);

    AuthResponse login(LoginRequest request);
}
