package edu.ufp.esof.order.services;

import edu.ufp.esof.order.models.OrderItem;

import java.util.Optional;
import java.util.Set;

public interface OrderService {
    OrderItem save(OrderItem order);

    Set<OrderItem> findAll();

    Optional<OrderItem> findById(Long id);
}
