package com.example.iam.service.impl;

import com.example.iam.model.RegistrationRequest;
import com.example.iam.peristence.entity.RoleEntity;
import com.example.iam.peristence.entity.UserEntity;
import com.example.iam.peristence.model.Role;
import com.example.iam.peristence.repository.UserRepository;
import com.example.iam.peristence.specification.UserSpecification;
import com.example.iam.service.UserService;
import com.example.iam.util.HandledExceptionFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity create(RegistrationRequest request, Collection<RoleEntity> roles) {
        return create(request.getEmail(), request.getName(), request.getPassword(), roles);
    }

    public UserEntity create(String email, String name, String password, Collection<RoleEntity> roles) {
        validateEmail(email);

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        return repository.save(user);
    }

    private void validateEmail(String email) {
        if (repository.existsByEmail(email))
            throw HandledExceptionFactory.getHandledException("Email already exists");
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

    @Override
    public UserEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("User not found"));
    }

    @Override
    public UserEntity update(Long userId, String name, String email, String password) {
        return update(findById(userId), name, email, password);
    }

    public UserEntity update(UserEntity user, String name, String email, String password) {
        if (!user.getEmail().equals(email))
            validateEmail(email);

        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return repository.save(user);
    }

    @Override
    public UserEntity deactivate(Long userId) {
        UserEntity user = findById(userId);
        user.setActive(false);
        return repository.save(user);
    }

    @Override
    public UserEntity activate(Long userId) {
        UserEntity user = findById(userId);
        user.setActive(true);
        return repository.save(user);
    }

    @Override
    public Page<UserEntity> filterUsers(List<Long> ids,
                                        String email,
                                        String name,
                                        Boolean active,
                                        Collection<Role> roles,
                                        int page,
                                        int size) {
        Specification<UserEntity> spec = Specification.where(UserSpecification.hasIds(ids))
                .and(UserSpecification.hasEmail(email))
                .and(UserSpecification.hasName(name))
                .and(UserSpecification.isActive(active))
                .and(UserSpecification.hasRoles(roles));
        return repository.findAll(spec, PageRequest.of(page, size));
    }

}
