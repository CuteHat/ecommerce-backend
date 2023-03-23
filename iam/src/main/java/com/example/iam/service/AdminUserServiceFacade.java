package com.example.iam.service;

import com.example.iam.model.UserDetailedResponse;
import com.example.iam.model.UserUpdateRequest;
import com.example.iam.peristence.model.Role;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface AdminUserServiceFacade {
    UserDetailedResponse get(Long id);

    Page<UserDetailedResponse> filter(List<Long> ids,
                                      String email,
                                      String name,
                                      Boolean active,
                                      Collection<Role> roles,
                                      int page,
                                      int size);

    void deactivate(Long id);

    void activate(Long id);

    UserDetailedResponse update(Long id, UserUpdateRequest request);

    UserDetailedResponse updateRole(Long id, Role role);
}
