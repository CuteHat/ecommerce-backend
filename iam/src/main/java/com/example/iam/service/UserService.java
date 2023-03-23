package com.example.iam.service;

import com.example.iam.model.RegistrationRequest;
import com.example.iam.model.UserUpdateRequest;
import com.example.iam.peristence.entity.RoleEntity;
import com.example.iam.peristence.entity.UserEntity;
import com.example.iam.peristence.model.Role;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface UserService {

    UserEntity create(RegistrationRequest request, Collection<RoleEntity> roles);

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findById(Long id);

    UserEntity update(Long userId, UserUpdateRequest request);

    UserEntity update(Long userId, String name, String email, String password, String phone, String address);

    UserEntity deactivate(Long userId);

    UserEntity activate(Long userId);

    Page<UserEntity> filterUsers(List<Long> ids,
                                 String email,
                                 String name,
                                 Boolean active,
                                 Collection<Role> roles,
                                 int page,
                                 int size);

    UserEntity updateRoles(Long userId, Collection<RoleEntity> roles);
}
