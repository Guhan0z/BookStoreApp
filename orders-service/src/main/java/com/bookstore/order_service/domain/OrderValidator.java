package com.bookstore.order_service.domain;

import com.bookstore.order_service.client.catalog.CatalogServiceClient;
import com.bookstore.order_service.client.catalog.Product;
import com.bookstore.order_service.domain.model.CreateOrderRequest;
import com.bookstore.order_service.domain.model.OrderItemDTO;
import com.bookstore.order_service.exception.InvalidOrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderValidator {
    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);

    private final CatalogServiceClient client;

    public OrderValidator(CatalogServiceClient client) {
        this.client = client;
    }

    public void validate(CreateOrderRequest request) {
        for(OrderItemDTO orderitem : request.orderItems()) {
            Product product = client.getProductByCode(orderitem.code())
                    .orElseThrow(() -> InvalidOrderException.forCode(orderitem.code()));
            if(orderitem.price().compareTo(product.price()) != 0) {
                log.info("Product price is not matching. Actual price: {} Received price: {}",
                        product.price(),
                        orderitem.price());

                throw new InvalidOrderException("orderitem.code()");
            }
        }
    }
}
