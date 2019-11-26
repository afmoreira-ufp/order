package edu.ufp.esof.order.services.filters;

import edu.ufp.esof.order.models.Client;
import edu.ufp.esof.order.models.LineOrder;
import edu.ufp.esof.order.models.OrderItem;
import edu.ufp.esof.order.models.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilterOrderServiceTest {

    @Test
    void filter() {

        String productName="product1";
        String clientName="client1";

        Client client=new Client(clientName);

        Set<OrderItem> orders=new HashSet<>();

        Product product=new Product(productName);

        OrderItem orderItem1=new OrderItem("12345");
        OrderItem orderItem2=new OrderItem("23456");
        OrderItem orderItem3=new OrderItem("34567");
        OrderItem orderItem4=new OrderItem("45678");

        LineOrder lineOrder1=new LineOrder(product,1);

        orderItem1.addLineOrder(lineOrder1);

        LineOrder lineOrder2=new LineOrder(new Product("product2"),1);

        orderItem2.addLineOrder(lineOrder1);
        orderItem2.addLineOrder(lineOrder2);

        client.addOrder(orderItem1);
        client.addOrder(orderItem3);

        orders.add(orderItem1);
        orders.add(orderItem2);
        orders.add(orderItem3);
        orders.add(orderItem4);


        FilterObject filterObject=new FilterObject(clientName,productName, LocalDate.now().minusDays(1),LocalDate.now().plusDays(1));
        FilterOrderService filterOrderService=new FilterOrderService();
        assertEquals(1,filterOrderService.filter(orders,filterObject).size());

    }
}