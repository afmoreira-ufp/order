package edu.ufp.esof.order.models;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderItem {

    private String orderNumber;
    private LocalDate orderDate;

    private Client client;

    private Set<LineOrder> lineOrders=new HashSet<>();

    public void addLineOrder(LineOrder lineOrder){
        this.lineOrders.add(lineOrder);
        lineOrder.setOrder(this);
    }

    @ToString.Include
    public float price(){
        float price=0;
        for(LineOrder lineOrder:this.lineOrders){
            price+=lineOrder.getQuantity()*lineOrder.getProduct().getPrice();
        }
        return price;
    }


}
