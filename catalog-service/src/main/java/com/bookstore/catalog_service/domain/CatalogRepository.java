package com.bookstore.catalog_service.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface CatalogRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByCode(String code);
}
