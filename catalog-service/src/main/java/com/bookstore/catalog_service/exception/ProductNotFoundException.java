package com.bookstore.catalog_service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Product not found with code " + code);
    }
}
