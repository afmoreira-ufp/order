package edu.ufp.esof.order.models;

import lombok.Data;

@Data
public class LineOrder {

    private OrderItem order;

    private int quantity;

    private Product product;


}
