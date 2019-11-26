package edu.ufp.esof.order.services.filters;


import edu.ufp.esof.order.models.OrderItem;

import java.util.Set;

public class AndOrderFilterOrder implements FilterOrderI {

    private FilterOrderI filter;
    private FilterOrderI otherFilter;

    public AndOrderFilterOrder(FilterOrderI filter, FilterOrderI otherFilter) {
        this.filter = filter;
        this.otherFilter = otherFilter;
    }

    @Override
    public Set<OrderItem> filter(Set<OrderItem> entities) {
        Set<OrderItem> filter1=this.filter.filter(entities);
        return this.otherFilter.filter(filter1);
    }
}
