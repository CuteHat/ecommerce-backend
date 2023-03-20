package com.example.iam.service;

import com.example.iam.entity.RoleEntity;
import com.example.iam.entity.UserEntity;
import com.example.iam.model.RegistrationRequest;

import java.util.Collection;

public interface UserService {

    UserEntity create(RegistrationRequest request, Collection<RoleEntity> roles);

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String email, String password);
}
