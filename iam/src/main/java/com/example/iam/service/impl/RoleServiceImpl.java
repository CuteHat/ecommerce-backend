package com.example.iam.service.impl;

import com.example.iam.peristence.entity.RoleEntity;
import com.example.iam.peristence.model.Role;
import com.example.iam.peristence.repository.RoleRepository;
import com.example.iam.service.RoleService;
import com.example.iam.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public RoleEntity getRoleByName(Role name) {
        return repository.findByName(name)
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("Role not found"));
    }

    @Override
    public RoleEntity getRoleByName(String name) {
        return getRoleByName(Role.valueOf(name));
    }

    @Override
    public RoleEntity getDefaultRole() {
        return getRoleByName(Role.CLIENT);
    }
}
