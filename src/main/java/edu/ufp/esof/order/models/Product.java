package edu.ufp.esof.order.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float price;

    @ManyToOne
    private Supplier supplier;

    public Product(String name,float price) {
        this.setName(name);
        this.setPrice(price);
    }

    public String supplierName(){
        return this.supplier.getName();
    }

}
