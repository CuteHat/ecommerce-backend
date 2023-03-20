package com.example.iam.repository;

import com.example.iam.entity.RoleEntity;
import com.example.iam.entity.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(Role role);
}
