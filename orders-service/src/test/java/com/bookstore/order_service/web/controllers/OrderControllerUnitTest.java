package com.bookstore.order_service.web.controllers;

import com.bookstore.order_service.domain.OrderService;
import com.bookstore.order_service.domain.SecurityService;
import com.bookstore.order_service.domain.model.CreateOrderRequest;
import com.bookstore.order_service.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.net.URI;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerUnitTest {

    @MockitoBean
    private OrderService orderService;
    @MockitoBean
    private SecurityService securityService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        BDDMockito.given(securityService.getLoggedInUsername()).willReturn("test");
    }

    @ParameterizedTest(name = "[{index}] - {0}")
    @MethodSource("createOrderRequest")
    void shouldReturnBadRequestWithInvalidRequestPayload(CreateOrderRequest request) throws Exception {
        BDDMockito.given(orderService.createOrder(eq("test"), any(CreateOrderRequest.class)))
                .willReturn(null);

        mvc.perform(post(URI.create("http://localhost:8082/api/orders/create"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    static Stream<Arguments> createOrderRequest() {
        return Stream.of(
                Arguments.arguments(Named.named("Order with invalid Customer details", TestDataUtil.createOrderRequestWithInvalidCustomer())),
                Arguments.arguments(Named.named("Order with invalid Delivery Address", TestDataUtil.createOrderRequestWithInvalidAddress())),
                Arguments.arguments(Named.named("Order with no items", TestDataUtil.createOrderRequestWithNoItems()))
        );
    }
}
