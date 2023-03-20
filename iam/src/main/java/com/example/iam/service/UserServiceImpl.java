package com.example.iam.service;

import com.example.iam.entity.UserEntity;
import com.example.iam.model.RegistrationRequest;
import com.example.iam.repository.UserRepository;
import com.example.iam.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(RegistrationRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return repository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("User not found"));
    }

}
