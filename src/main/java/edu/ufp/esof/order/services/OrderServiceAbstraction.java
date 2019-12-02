package edu.ufp.esof.order.services;

import edu.ufp.esof.order.models.OrderItem;
import edu.ufp.esof.order.services.authentication.LoginService;
import edu.ufp.esof.order.services.filters.FilterObject;
import edu.ufp.esof.order.services.filters.FilterOrderService;
import edu.ufp.esof.order.services.orderoutput.OrderOutPutPDF;
import edu.ufp.esof.order.services.orderoutput.OrderOutput;
import edu.ufp.esof.order.services.orderoutput.OrderOutputDocx;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public abstract class OrderServiceAbstraction implements OrderService,OrderOutput{

    @Getter
    @Setter
    private OrderOutput orderOutput;

    private FilterOrderService filterOrderService;
    private LoginService loginService;

    @Autowired
    public OrderServiceAbstraction(FilterOrderService filterOrderService, LoginService loginService) {
        this.filterOrderService = filterOrderService;
        this.loginService = loginService;
    }

    public byte[] outputFile(OrderItem order, String type){
        if(type.equalsIgnoreCase("pdf")){
            this.setOutputPDF();
        }else{
            this.setOutputDoc();
        }
        return this.orderOutput.outputFile(order,type);
    }


    public void setOutputPDF(){
        this.orderOutput=new OrderOutPutPDF();
    }

    public void setOutputDoc(){
        this.orderOutput=new OrderOutputDocx();
    }


    public Set<OrderItem> filterOrders(Map<String, String> searchParams) {

        FilterObject filterObject=new FilterObject(searchParams);
        Set<OrderItem> orderItems=this.findAll();

        return this.filterOrderService.filter(orderItems,filterObject);
    }

    public Optional<OrderItem> accessOrder(Long id, String token){
        Optional<OrderItem> optionalOrderItem=this.findById(id);
        if(optionalOrderItem.isPresent()){
            OrderItem order=optionalOrderItem.get();
            if(this.loginService.authenticateUser(order.getClient(),token)){
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }
}
