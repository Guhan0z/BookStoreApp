package com.bookstore.order_service.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderItemDTO(
        @NotBlank(message = "Code is required") String code,
        @NotBlank(message = "Code is required") String name,
        @NotNull(message = "Code is required") BigDecimal price,
        @NotNull(message = "Code is required") @Min(value = 1, message = "Minimum quantity is 1") Integer quantity) {}
