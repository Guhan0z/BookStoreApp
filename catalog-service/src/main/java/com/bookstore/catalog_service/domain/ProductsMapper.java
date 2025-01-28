package com.bookstore.catalog_service.domain;

class ProductsMapper {

    static ProductDTO toProductDTO(ProductEntity productEntity) {
        return new ProductDTO(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImage_url(),
                productEntity.getPrice());
    }
}
