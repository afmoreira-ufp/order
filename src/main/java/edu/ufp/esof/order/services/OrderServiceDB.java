package edu.ufp.esof.order.services;

import edu.ufp.esof.order.models.OrderItem;
import edu.ufp.esof.order.repositories.OrderRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile(value = "db")
public class OrderServiceDB extends OrderServiceAbstraction {

    private OrderRepo orderRepo;

    public OrderServiceDB(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public OrderItem save(OrderItem order) {
        return this.orderRepo.save(order);
    }

    @Override
    public Iterable<OrderItem> findAll() {
        return this.orderRepo.findAll();
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        Optional<OrderItem> optionalOrder= this.orderRepo.findById(id);
        return optionalOrder;
    }

}
