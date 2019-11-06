package edu.ufp.esof.order.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private LocalDate orderDate;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Client client;

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private Set<LineOrder> lineOrders=new HashSet<>();

    public OrderItem(){
        this.orderDate=LocalDate.now();
    }

    public OrderItem(String orderNumber) {
        this();
        this.orderNumber=orderNumber;
    }

    public void addLineOrder(LineOrder lineOrder){
        this.lineOrders.add(lineOrder);
        lineOrder.setOrder(this);
    }

    @ToString.Include
    public float price(){
        float price=0;
        for(LineOrder lineOrder:this.lineOrders){
            price+=lineOrder.getQuantity()*lineOrder.getProduct().getPrice();
        }
        return price;
    }


}
