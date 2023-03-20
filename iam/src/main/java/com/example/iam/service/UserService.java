package com.example.iam.service;

import com.example.iam.entity.UserEntity;
import com.example.iam.model.RegistrationRequest;

public interface UserService {
    UserEntity create(RegistrationRequest request);

    UserEntity getByEmail(String email);
}
