package com.example.iam.service.impl;

import com.example.iam.model.UserResponse;
import com.example.iam.model.UserUpdateRequest;
import com.example.iam.peristence.entity.UserEntity;
import com.example.iam.service.UserService;
import com.example.iam.service.UserServiceFacade;
import com.example.iam.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceFacadeImpl implements UserServiceFacade {
    private final UserService userService;

    @Override
    public UserResponse get() {
        UserEntity user = userService.findById(SecurityUtils.getAuthenticatedUserId());
        return UserResponse.transform(user);
    }

    @Override
    public UserResponse update(UserUpdateRequest request) {
        UserEntity user = userService.update(SecurityUtils.getAuthenticatedUserId(), request);
        return UserResponse.transform(user);
    }

    @Override
    public void deactivate() {
        userService.deactivate(SecurityUtils.getAuthenticatedUserId());
    }
}
