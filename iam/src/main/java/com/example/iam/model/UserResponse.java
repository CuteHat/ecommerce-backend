package com.example.iam.model;

import com.example.iam.peristence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;

    public static UserResponse transform(UserEntity entity) {
        return new UserResponse(entity.getId(), entity.getEmail(), entity.getName());
    }
}
