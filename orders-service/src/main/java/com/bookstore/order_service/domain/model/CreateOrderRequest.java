package com.bookstore.order_service.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record CreateOrderRequest(
        @Valid Customer customer,
        @Valid Address address,
        @Valid @NotEmpty(message = "Order Items cannot be empty") Set<OrderItemDTO> orderItems) {}
