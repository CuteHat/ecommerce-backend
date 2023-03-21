package com.example.iam.service.impl;

import com.example.iam.model.UserDetailedResponse;
import com.example.iam.peristence.entity.UserEntity;
import com.example.iam.peristence.model.Role;
import com.example.iam.service.AdminUserServiceFacade;
import com.example.iam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserServiceFacadeImpl implements AdminUserServiceFacade {
    private final UserService userService;

    @Override
    public UserDetailedResponse get(Long id) {
        UserEntity user = userService.findById(id);
        return UserDetailedResponse.transform(user);
    }

    @Override
    public Page<UserDetailedResponse> filter(List<Long> ids,
                                             String email,
                                             String name,
                                             Boolean active,
                                             Collection<Role> roles,
                                             int page,
                                             int size) {
        Page<UserEntity> filteredUsers = userService.filterUsers(ids, email, name, active, roles, page, size);
        return mapToUserDtoPage(filteredUsers);
    }

    @Override
    public void deactivate(Long id) {
        userService.deactivate(id);
    }

    @Override
    public void activate(Long id) {
        userService.activate(id);
    }

    @Override
    public UserDetailedResponse update(Long id, String name, String email, String password) {
        UserEntity user = userService.update(id, name, email, password);
        return UserDetailedResponse.transform(user);
    }

    private Page<UserDetailedResponse> mapToUserDtoPage(Page<UserEntity> entityPage) {
        List<UserDetailedResponse> dtoList = entityPage.getContent()
                .stream()
                .map(UserDetailedResponse::transform)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }

}
