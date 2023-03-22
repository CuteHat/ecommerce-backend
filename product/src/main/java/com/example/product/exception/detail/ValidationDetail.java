package com.example.product.exception.detail;

import com.example.product.exception.model.FieldValidationError;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationDetail extends HandledExceptionDetail {
    private final List<FieldValidationError> fieldValidations;

    public ValidationDetail(List<FieldValidationError> fieldValidations) {
        super("validation error");
        this.fieldValidations = fieldValidations;
    }
}
