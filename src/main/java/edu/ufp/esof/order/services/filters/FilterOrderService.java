package edu.ufp.esof.order.services.filters;

import edu.ufp.esof.order.models.OrderItem;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilterOrderService{

    public Set<OrderItem> filter(Set<OrderItem> orders, FilterObject filterObject) {
        FilterOrderI productNameFIlter=new OrderFilterProductName(filterObject.getProductName());
        FilterOrderI clientNameFilter=new OrderFilterClientName(filterObject.getClientName());

        FilterOrderI startDateFilter=new OrderFilterOrderStartDate(filterObject.getStartDate());
        FilterOrderI endDateFilter=new OrderFilterOrderEndDate(filterObject.getEndDate());

        FilterOrderI productNameAndClientName=new AndOrderFilterOrder(productNameFIlter,clientNameFilter);
        FilterOrderI startDateAndEndDate=new AndOrderFilterOrder(startDateFilter,endDateFilter);

        return new AndOrderFilterOrder(productNameAndClientName,startDateAndEndDate).filter(orders);
    }
}
