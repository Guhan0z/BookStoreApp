package com.bookstore.catalog_service.web.controllers;

import com.bookstore.catalog_service.domain.CatalogService;
import com.bookstore.catalog_service.domain.ProductDTO;
import com.bookstore.catalog_service.domain.ProductsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
class CatalogController {

    private final CatalogService catalogService;

    CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    ProductsResponse getProducts(@RequestParam("pageNo") int pageNo) {
        return catalogService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductDTO> getProductByCode(@PathVariable(name = "code") String code) {
        return new ResponseEntity<>(catalogService.getProductByCode(code), HttpStatus.OK);
    }
}
