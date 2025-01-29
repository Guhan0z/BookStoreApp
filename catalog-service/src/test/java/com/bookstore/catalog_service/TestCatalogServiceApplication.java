package com.bookstore.catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = {"classpath:application-dev.yml", "classpath:messages.properties"})
public class TestCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CatalogServiceApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
