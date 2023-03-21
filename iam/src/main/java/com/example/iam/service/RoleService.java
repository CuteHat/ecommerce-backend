package com.example.iam.service;

import com.example.iam.peristence.entity.RoleEntity;
import com.example.iam.peristence.model.Role;

public interface RoleService {
    RoleEntity getRoleByName(Role name);

    RoleEntity getRoleByName(String name);

    RoleEntity getDefaultRole();
}
