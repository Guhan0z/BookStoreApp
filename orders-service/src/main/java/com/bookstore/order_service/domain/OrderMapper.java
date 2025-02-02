package com.bookstore.order_service.domain;

import com.bookstore.order_service.domain.model.CreateOrderRequest;
import com.bookstore.order_service.domain.model.OrderItemDTO;
import com.bookstore.order_service.domain.model.OrderStatus;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class OrderMapper {
    public static OrderEntity toOrderEntity(String userName, CreateOrderRequest request) {
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAddress(request.address());
        order.setCustomer(request.customer());
        order.setStatus(OrderStatus.NEW);
        order.setUsername(userName);
        Set<OrderItemEntity> orderItems = new HashSet<>();
        for (OrderItemDTO item : request.orderItems()) {
            OrderItemEntity entity = new OrderItemEntity();
            entity.setName(item.name());
            entity.setCode(item.code());
            entity.setPrice(item.price());
            entity.setQuantity(item.quantity());
            entity.setOrderId(order);
            orderItems.add(entity);
        }
        order.setOrderItem(orderItems);
        return order;
    }
}
