package com.example.iam.service;

import com.example.iam.model.UserResponse;
import com.example.iam.model.UserUpdateRequest;

public interface UserServiceFacade {
    UserResponse get();

    UserResponse update(UserUpdateRequest request);

    void deactivate();
}
