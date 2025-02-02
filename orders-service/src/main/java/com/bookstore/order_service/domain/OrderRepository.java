package com.bookstore.order_service.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Always create repository for AGGREGATE ROOT entities, in our case OrderEntity and not OrderItemEntity
 * because w/o Orders there is no OrderItems
 */
interface OrderRepository extends JpaRepository<OrderEntity, Long> {}
