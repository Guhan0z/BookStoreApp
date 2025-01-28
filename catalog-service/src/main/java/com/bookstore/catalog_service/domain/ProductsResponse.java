package com.bookstore.catalog_service.domain;

import java.util.List;

public record ProductsResponse(
        List<ProductDTO> productList,
        long totalElements,
        int totalPages,
        int pageNumber,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious) {}
