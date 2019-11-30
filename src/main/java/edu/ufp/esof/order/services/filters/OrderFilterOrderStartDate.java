package edu.ufp.esof.order.services.filters;

import edu.ufp.esof.order.models.OrderItem;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OrderFilterOrderStartDate implements FilterOrderI {

    private LocalDate startDate;

    public OrderFilterOrderStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public Set<OrderItem> filter(Set<OrderItem> orders) {
        if(this.startDate==null){
            return orders;
        }
        Set<OrderItem> ordersToReturn=new HashSet<>();
        for(OrderItem oi:orders){
            if(oi!=null && oi.getOrderDate()!=null && (oi.getOrderDate().isAfter(this.startDate) || oi.getOrderDate().equals(this.startDate))){
                ordersToReturn.add(oi);
            }
        }
        return ordersToReturn;
    }
}
