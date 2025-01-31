package com.bookstore.order_service.config;

import com.bookstore.order_service.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private final ApplicationProperties applicationProperties;

    public RabbitMQConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(applicationProperties.orderEventsExchange());
    }

    @Bean
    Queue newOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrdersQueueBinding(DirectExchange exchange) {
        return BindingBuilder.bind(newOrdersQueue()).to(exchange).with(applicationProperties.newOrdersQueue());
    }

    @Bean
    Queue deliveredOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.deliveredOrdersQueue())
                .build();
    }

    @Bean
    Binding deliveredOrdersQueueBinding(DirectExchange exchange) {
        return BindingBuilder.bind(deliveredOrdersQueue())
                .to(exchange)
                .with(applicationProperties.deliveredOrdersQueue());
    }

    @Bean
    Queue cancelledOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.cancelledOrdersQueue())
                .build();
    }

    @Bean
    Binding cancelledOrdersQueueBinding(DirectExchange exchange) {
        return BindingBuilder.bind(cancelledOrdersQueue())
                .to(exchange)
                .with(applicationProperties.cancelledOrdersQueue());
    }

    @Bean
    Queue errorOrdersQueue() {
        return QueueBuilder.durable(applicationProperties.errorOrdersQueue()).build();
    }

    @Bean
    Binding errorOrdersQueueBinding(DirectExchange exchange) {
        return BindingBuilder.bind(errorOrdersQueue()).to(exchange).with(applicationProperties.errorOrdersQueue());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
