package com.example.iam.service;

import com.example.iam.entity.RoleEntity;
import com.example.iam.entity.model.Role;

public interface RoleService {
    RoleEntity getRoleByName(Role name);

    RoleEntity getRoleByName(String name);

    RoleEntity getDefaultRole();
}
