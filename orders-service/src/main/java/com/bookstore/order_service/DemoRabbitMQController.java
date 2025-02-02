package com.bookstore.order_service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRabbitMQController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ApplicationProperties applicationProperties;

    @PostMapping("/test")
    void message(@RequestParam String routingKey, @RequestParam String msg) {
        rabbitTemplate.convertAndSend(applicationProperties.orderEventsExchange(), routingKey, msg);
    }
}
