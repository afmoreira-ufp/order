package edu.ufp.esof.order.services.filters;

import edu.ufp.esof.order.models.LineOrder;
import edu.ufp.esof.order.models.OrderItem;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OrderFilterProductName implements FilterOrderI {

    private String productName;

    public OrderFilterProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public Set<OrderItem> filter(Set<OrderItem> orders) {
        if(this.productName==null || this.productName.isBlank()){
            return orders;
        }

        Set<OrderItem> ordersToBeReturned=new HashSet<>();
        for(OrderItem oi:orders){
            for(LineOrder lo:oi.getLineOrders()){
                if(lo!=null && lo.getProduct()!=null && lo.getProduct().getName().equals(productName)){
                    ordersToBeReturned.add(oi);
                    break;
                }
            }
        }

        return ordersToBeReturned;
    }
}
