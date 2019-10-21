package edu.ufp.esof.order.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Supplier {

    private String name;

    private Set<Product> products=new HashSet<>();

    public Supplier(String name) {
        this.setName(name);
    }

    public void addProduct(Product product){
        this.products.add(product);
        product.setSupplier(this);
    }
}
