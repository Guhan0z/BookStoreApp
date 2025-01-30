package com.bookstore.catalog_service.web.controllers;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import com.bookstore.catalog_service.AbstractTest;
import com.bookstore.catalog_service.domain.ProductDTO;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test_data.sql")
class CatalogControllerTest extends AbstractTest {

    @Test
    void shouldGetProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products?pageNo=1")
                .then()
                //                .log().everything()
                .statusCode(200)
                .body("productList", hasSize(10))
                .body("totalElements", is(15))
                .body("totalPages", is(2))
                .body("pageNumber", is(1))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode() {
        ProductDTO product = when().get("/api/products/P103")
                .then()
                //                .log().everything()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductDTO.class);

        assertThat(product.code()).isEqualTo("P103");
        assertThat(product.name()).isEqualTo("Gone with the Wind");
        assertThat(product.description())
                .isEqualTo("Gone with the Wind is a novel written by Margaret Mitchell, first published in 1936.");
        assertThat(product.image_url()).isEqualTo("https://images.gr-assets.com/books/1328025229l/18405.jpg");
        assertThat(product.price()).isEqualTo(new BigDecimal("44.50"));
    }

    @Test
    void shoundReturnProductNotFoundException() {
        String code = "invalid_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("api/products/" + code)
                .then()
                .statusCode(404)
                .body("title", equalTo("Product Not Found"))
                .body("status", is(404))
                .body("service", equalTo("catalog-service"))
                .body("error_category", equalTo("Generic"));
    }
}
