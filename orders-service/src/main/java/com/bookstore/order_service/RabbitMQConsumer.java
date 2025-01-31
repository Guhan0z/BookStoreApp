package com.bookstore.order_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void listner(String msg) {
        System.out.println("New orders queue - " + msg);
    }
}
