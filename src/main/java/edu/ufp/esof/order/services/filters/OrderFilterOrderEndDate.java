package edu.ufp.esof.order.services.filters;

import edu.ufp.esof.order.models.OrderItem;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class OrderFilterOrderEndDate implements FilterOrderI {

    private LocalDate endDate;

    public OrderFilterOrderEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public Set<OrderItem> filter(Set<OrderItem> orders) {
        if(this.endDate==null){
            return orders;
        }
        Set<OrderItem> ordersToBeReturned=new HashSet<>();
        for(OrderItem oi:orders){
            if(oi!=null && oi.getOrderDate()!=null && (oi.getOrderDate().isBefore(endDate) || oi.getOrderDate().equals(endDate))){
                ordersToBeReturned.add(oi);
            }
        }
        return ordersToBeReturned;
    }
}
