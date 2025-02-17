package com.bookstore.order_service.domain;

import com.bookstore.order_service.domain.model.CreateOrderRequest;
import com.bookstore.order_service.domain.model.CreateOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    public OrderService(OrderRepository orderRepository, OrderValidator validator) {
        this.orderRepository = orderRepository;
        this.orderValidator = validator;
    }

    public CreateOrderResponse createOrder(String userName, CreateOrderRequest request) {
        orderValidator.validate(request);
        OrderEntity order = OrderMapper.toOrderEntity(userName, request);
        OrderEntity savedOrder = orderRepository.save(order);
        log.info("Order created with order-id {}", savedOrder.getOrderNumber());
        return CreateOrderResponse.builder()
                .orderNumber(savedOrder.getOrderNumber())
                .build();
    }
}
