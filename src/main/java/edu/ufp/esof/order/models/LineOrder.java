package edu.ufp.esof.order.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class LineOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderItem order;

    private int quantity;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Product product;


    public LineOrder(Product product, int quantity) {
        this.setProduct(product);
        this.setQuantity(quantity);
    }
}
