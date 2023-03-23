package com.example.order.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCreateRequest {
    @NotNull
    @Size(min = 1)
    @Valid
    private List<OrderItem> orderItems;
    @NotEmpty
    @Pattern(regexp = "\\d+")
    private String customerPhone;
}
