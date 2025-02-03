package com.bookstore.order_service.web.controllers;

import com.bookstore.order_service.AbstractTest;
import com.bookstore.order_service.util.TestDataUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

class OrderControllerTest extends AbstractTest {

    @Nested
    class createOrderTest {
        @Test
        void shouldCreateOrder() {
            given().contentType(ContentType.JSON)
                    .body(TestDataUtil.createValidOrderRequest())
                    .when()
                    .post("/api/orders/create")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWithInvalidRequestPayload() {
            given().contentType(ContentType.JSON)
                    .when()
                    .post("api/orders/create")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}