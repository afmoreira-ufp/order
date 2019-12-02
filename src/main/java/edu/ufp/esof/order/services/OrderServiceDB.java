package edu.ufp.esof.order.services;

import edu.ufp.esof.order.models.OrderItem;
import edu.ufp.esof.order.repositories.OrderRepo;
import edu.ufp.esof.order.services.authentication.LoginService;
import edu.ufp.esof.order.services.filters.FilterOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile(value = "db")
public class OrderServiceDB extends OrderServiceAbstraction {

    private OrderRepo orderRepo;

    @Autowired
    public OrderServiceDB( FilterOrderService filterOrderService, LoginService loginService, OrderRepo orderRepo) {
        super(filterOrderService, loginService);
        this.orderRepo = orderRepo;
    }


    @Override
    public OrderItem save(OrderItem order) {
        return this.orderRepo.save(order);
    }

    @Override
    public Set<OrderItem> findAll() {
        Iterable<OrderItem> orderItems= this.orderRepo.findAll();

        Set<OrderItem> orderSet=new HashSet<>();
        for(OrderItem oi:orderItems){
            orderSet.add(oi);
        }

        return orderSet;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {

        return this.orderRepo.findById(id);
    }
}
