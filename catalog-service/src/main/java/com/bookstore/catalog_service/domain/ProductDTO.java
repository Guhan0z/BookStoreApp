package com.bookstore.catalog_service.domain;

import java.math.BigDecimal;

public record ProductDTO(String code, String name, String description, String image_url, BigDecimal price) {}
