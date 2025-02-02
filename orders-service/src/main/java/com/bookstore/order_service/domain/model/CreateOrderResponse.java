package com.bookstore.order_service.domain.model;

import lombok.Builder;

@Builder
public record CreateOrderResponse(String orderNumber) {}
