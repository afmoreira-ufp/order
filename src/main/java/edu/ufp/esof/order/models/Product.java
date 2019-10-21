package edu.ufp.esof.order.models;

import lombok.Data;

@Data
public class Product {

    private String name;

    private float price;

    private Supplier supplier;

    public Product(String name,float price) {
        this.setName(name);
        this.setPrice(price);
    }

    public String supplierName(){
        return this.supplier.getName();
    }

}
