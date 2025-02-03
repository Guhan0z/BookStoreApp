package com.bookstore.order_service.web.controllers;

import com.bookstore.order_service.domain.OrderService;
import com.bookstore.order_service.domain.SecurityService;
import com.bookstore.order_service.domain.model.CreateOrderRequest;
import com.bookstore.order_service.domain.model.CreateOrderResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final SecurityService securityService;

    OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        String userName = securityService.getLoggedInUsername();
        log.info("Creating order for user {}", userName);
        return orderService.createOrder(userName, request);
    }
}
