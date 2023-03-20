package com.example.iam.service.impl;

import com.example.iam.entity.RoleEntity;
import com.example.iam.entity.UserEntity;
import com.example.iam.model.RegistrationRequest;
import com.example.iam.repository.UserRepository;
import com.example.iam.service.UserService;
import com.example.iam.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(RegistrationRequest request, Collection<RoleEntity> roles) {
        return create(request.getEmail(), request.getName(), request.getPassword(), roles);
    }

    public UserEntity create(String email, String name, String password, Collection<RoleEntity> roles) {
        if (repository.existsByEmail(email))
            throw HandledExceptionFactory.getHandledException("Email already exists");

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        return repository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("User not found"));
    }

    @Override
    public UserEntity findByEmailAndPassword(String email, String password) {
        UserEntity user = findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw HandledExceptionFactory.getHandledException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        return user;
    }

}
