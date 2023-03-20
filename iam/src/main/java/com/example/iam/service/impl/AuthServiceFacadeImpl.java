package com.example.iam.service.impl;

import com.example.iam.entity.RoleEntity;
import com.example.iam.entity.UserEntity;
import com.example.iam.model.AuthResponse;
import com.example.iam.model.LoginRequest;
import com.example.iam.model.RegistrationRequest;
import com.example.iam.service.AuthServiceFacade;
import com.example.iam.service.JwtService;
import com.example.iam.service.RoleService;
import com.example.iam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceFacadeImpl implements AuthServiceFacade {
    private final UserService userService;
    private final JwtService jwtService;
    private final RoleService roleService;

    @Override
    public AuthResponse register(RegistrationRequest request) {
        UserEntity newUser = userService.create(request, getDefaultRoles());
        return getAuthResponse(newUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        UserEntity user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        return getAuthResponse(user);
    }

    private AuthResponse getAuthResponse(UserEntity user) {
        String sub = user.getId().toString();
        Collection<RoleEntity> roles = user.getRoles();
        return AuthResponse.builder()
                .accessToken(jwtService.generateAccessToken(sub, roles.stream().map(roleEntity -> roleEntity.getName().name()).toList()))
                .idToken(jwtService.generateIdToken(sub, user.getEmail(), user.getName()))
                .build();
    }

    private List<RoleEntity> getDefaultRoles() {
        return List.of(roleService.getDefaultRole());
    }

}
