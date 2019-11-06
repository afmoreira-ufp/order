package edu.ufp.esof.order.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Supplier supplier;

    public Product(String name,float price) {
        this.setName(name);
        this.setPrice(price);
    }

    public Product(String name) {
        this.setName(name);
    }

    public String supplierName(){
        return this.supplier.getName();
    }

}
