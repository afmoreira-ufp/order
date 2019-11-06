package edu.ufp.esof.order.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "client",cascade = CascadeType.PERSIST)
    private Set<OrderItem> orders=new HashSet<>();

    public Client(String name) {
        this.setName(name);
    }

    public void addOrder(OrderItem order){
        this.orders.add(order);
        order.setClient(this);
    }

}
