package edu.ufp.esof.order.services.filters;

import edu.ufp.esof.order.models.OrderItem;

import java.util.Set;

public interface FilterOrderI {
    Set<OrderItem> filter(Set<OrderItem> orders);
}
