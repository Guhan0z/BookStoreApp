package com.bookstore.order_service.domain.model;

import jakarta.validation.constraints.NotBlank;

public record Address(
        @NotBlank(message = "AddressLine1 is required") String addressLine1,
        String addressLine2,
        @NotBlank(message = "City is required") String city,
        @NotBlank(message = "State is required") String state,
        @NotBlank(message = "PinCode is required") String pinCode,
        @NotBlank(message = "Country is required") String country) {}
