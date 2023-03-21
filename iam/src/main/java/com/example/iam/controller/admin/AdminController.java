package com.example.iam.controller.admin;

import com.example.iam.model.UserDetailedResponse;
import com.example.iam.peristence.model.Role;
import com.example.iam.service.AdminUserServiceFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/user")
@RequiredArgsConstructor
public class AdminController {
    private final AdminUserServiceFacade userServiceFacade;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailedResponse> getUser(@PathVariable("id") @Min(1) Long id) {
        UserDetailedResponse user = userServiceFacade.get(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<UserDetailedResponse>> filterUsers(@RequestParam(required = false) List<Long> ids,
                                                                  @RequestParam(required = false) String email,
                                                                  @RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) Boolean active,
                                                                  @RequestParam(required = false) Collection<Role> roles,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<UserDetailedResponse> filteredUsers = userServiceFacade.filter(ids, email, name, active, roles, page, size);
        return ResponseEntity.ok(filteredUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailedResponse> updateUser(@PathVariable("id") Long id,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String email,
                                                           @RequestParam(required = false) String password) {
        UserDetailedResponse user = userServiceFacade.update(id, name, email, password);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") @Min(1) Long id) {
        userServiceFacade.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable("id") @Min(1) Long id) {
        userServiceFacade.activate(id);
        return ResponseEntity.ok().build();
    }
}
