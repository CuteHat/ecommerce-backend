package com.example.iam.model;

import com.example.iam.exception.validation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Length(max = 64)
    private String name;
    @NotEmpty
    @Length(min = 8, max = 64)
    @StrongPassword
    private String password;
    @NotEmpty
    @Pattern(regexp = "\\d+")
    private String phone;
    @NotEmpty
    private String address;
}
