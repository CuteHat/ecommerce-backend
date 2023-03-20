package com.example.iam.exception.validation;

import com.example.iam.util.PasswordUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return PasswordUtils.isPasswordStrong(password);
    }
}
