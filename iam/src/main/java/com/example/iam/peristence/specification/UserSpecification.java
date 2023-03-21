package com.example.iam.peristence.specification;

import com.example.iam.peristence.entity.RoleEntity;
import com.example.iam.peristence.entity.UserEntity;
import com.example.iam.peristence.model.Role;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

public class UserSpecification {
    public static Specification<UserEntity> hasIds(List<Long> ids) {
        return (root, query, builder) -> {
            if (ids == null) return builder.conjunction();
            return root.get(UserEntity.Fields.id).in(ids);
        };
    }

    public static Specification<UserEntity> hasEmail(String email) {
        return (root, query, builder) -> {
            if (email == null) return builder.conjunction();
            return builder.like(root.get(UserEntity.Fields.email), "%" + email + "%");
        };
    }

    public static Specification<UserEntity> hasName(String name) {
        return (root, query, builder) -> {
            if (name == null) return builder.conjunction();
            return builder.like(root.get(UserEntity.Fields.name), "%" + name + "%");
        };
    }

    public static Specification<UserEntity> isActive(Boolean active) {
        return (root, query, builder) -> {
            if (active == null) return builder.conjunction();
            return builder.equal(root.get(UserEntity.Fields.active), active);
        };
    }

    public static Specification<UserEntity> hasRoles(Collection<Role> roles) {
        return (root, query, builder) -> {
            if (roles == null || roles.isEmpty()) return builder.conjunction();

            Join<UserEntity, RoleEntity> join = root.join(UserEntity.Fields.roles);
            return join.get(RoleEntity.Fields.name).in(roles);
        };
    }
}


