package edu.ufp.esof.order.services;

import edu.ufp.esof.order.models.OrderItem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("inmemory")
public class OrderServiceInMemory extends OrderServiceAbstraction{

    private Set<OrderItem> orders=new HashSet<>();
    private static Long id_count;


    @Override
    public OrderItem save(OrderItem order) {
        order.setId(id_count++);
        this.orders.add(order);
        return order;
    }

    @Override
    public Iterable<OrderItem> findAll() {
        return this.orders;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        for(OrderItem order:this.orders){
            if(order.getId().equals(id)){
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }
}
