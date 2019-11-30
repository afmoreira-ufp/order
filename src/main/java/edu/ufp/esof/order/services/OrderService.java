package edu.ufp.esof.order.services;

import edu.ufp.esof.order.models.OrderItem;

import java.util.Optional;

public interface OrderService {
    OrderItem save(OrderItem order);

    Iterable<OrderItem> findAll();

    Optional<OrderItem> findById(Long id);
}
