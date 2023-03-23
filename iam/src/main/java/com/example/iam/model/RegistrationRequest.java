package com.example.iam.model;

import com.example.iam.exception.validation.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
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
    @Size(min = 9, max = 10)
    @Pattern(regexp = "\\d+")
    private String phone;
    @NotEmpty
    private String address;
}
