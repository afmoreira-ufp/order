package edu.ufp.esof.order.services.filters;

import edu.ufp.esof.order.models.OrderItem;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OrderFilterClientName implements FilterOrderI {

    private String clientName;

    public OrderFilterClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public Set<OrderItem> filter(Set<OrderItem> orders) {
        if(this.clientName==null || this.clientName.isBlank()){
            return orders;
        }

        Set<OrderItem> ordersToBeReturned=new HashSet<>();
        for(OrderItem oi:orders){
            if(oi.getClient()!=null && oi.getClient().getName()!=null && oi.getClient().getName().equals(clientName)){
                ordersToBeReturned.add(oi);
            }
        }
        return ordersToBeReturned;
    }
}
