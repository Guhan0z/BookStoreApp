package com.bookstore.order_service.config;

import com.bookstore.order_service.ApplicationProperties;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class CatalogServiceCilentConfig {

    @Bean
    RestClient restClient(ApplicationProperties properties) {
        //Configuring timeout/time limiter
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactoryBuilder.simple()
                .withCustomizer(customizer -> {
                    customizer.setConnectTimeout(Duration.ofSeconds(5));
                    customizer.setReadTimeout(Duration.ofSeconds(5));
                }).build();
        return RestClient.builder()
                .baseUrl(properties.orderServiceBaseUrl())
                .requestFactory(requestFactory)
                .build();
    }
}
