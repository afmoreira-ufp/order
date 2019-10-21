package edu.ufp.esof.order.models;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Client {

    private String name;
    private String address;
    private String phone;

    private Set<OrderItem> orders=new HashSet<>();

    public Client(String name) {
        this.setName(name);
    }

    public void addOrder(OrderItem order){
        this.orders.add(order);
        order.setClient(this);
    }

}
