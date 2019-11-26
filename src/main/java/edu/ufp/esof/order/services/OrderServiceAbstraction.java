package edu.ufp.esof.order.services;

import edu.ufp.esof.order.models.OrderItem;
import edu.ufp.esof.order.services.orderoutput.OrderOutPutPDF;
import edu.ufp.esof.order.services.orderoutput.OrderOutput;
import edu.ufp.esof.order.services.orderoutput.OrderOutputDocx;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public abstract class OrderServiceAbstraction implements OrderService,OrderOutput{
    @Getter
    @Setter
    private OrderOutput orderOutput;

    public byte[] outputFile(OrderItem order){
        return orderOutput.outputFile(order);
    }

    public void setOutputPDF(){
        this.orderOutput=new OrderOutPutPDF();
    }

    public void setOutputDocx(){
        this.orderOutput=new OrderOutputDocx();
    }
}
