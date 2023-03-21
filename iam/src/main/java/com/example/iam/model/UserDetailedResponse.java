package com.example.iam.model;

import com.example.iam.peristence.entity.RoleEntity;
import com.example.iam.peristence.entity.UserEntity;
import com.example.iam.peristence.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailedResponse {
    private Long id;
    private String email;
    private String name;
    private Boolean active;
    private List<Role> roles;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public static UserDetailedResponse transform(UserEntity user) {
        return UserDetailedResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .active(user.getActive())
                .roles(user.getRoles().stream().map(RoleEntity::getName).toList())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
