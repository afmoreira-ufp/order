package edu.ufp.esof.order.controllers;

import edu.ufp.esof.order.models.Client;
import edu.ufp.esof.order.models.OrderItem;
import edu.ufp.esof.order.repositories.ClientRepo;
import edu.ufp.esof.order.services.OrderService;
import edu.ufp.esof.order.services.OrderServiceAbstraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {

    private static final String WORD_MIME_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    @Autowired
    private OrderServiceAbstraction orderService;

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<OrderItem>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.findAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderItem> createOrder(@RequestBody OrderItem order) {
        Client client = order.getClient();
        Optional<Client> optionalClient=clientRepo.findByName(client.getName());
        if (optionalClient.isEmpty()) {
            client=clientRepo.save(client);
        }else{
            client=optionalClient.get();
        }
        order.setClient(client);

        return ResponseEntity.ok(orderService.save(order));
    }

    @GetMapping(value="/{format}/{id}", produces = {OrderController.WORD_MIME_TYPE,MediaType.ALL_VALUE} )
    public ResponseEntity<byte[]> getPDF(@PathVariable Long id, @PathVariable String format) {
        Optional<OrderItem> optionalOrderItem=this.orderService.findById(id);
        if(optionalOrderItem.isEmpty()){
            throw new NoOrderExcpetion(id);
        }
        OrderItem order=optionalOrderItem.get();

        if(format.equalsIgnoreCase("pdf")) {

            this.orderService.setOutputPDF();
            byte[] fileStream = this.orderService.outputFile(order);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            String filename = "order" + order.getId() + ".pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(fileStream, headers, HttpStatus.OK);
        }
        else{
            this.orderService.setOutputDocx();
            byte[] fileStream=this.orderService.outputFile(order);

            HttpHeaders headers = new HttpHeaders();
            String filename = "order"+order.getId()+".docx";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(fileStream, headers, HttpStatus.OK);
        }
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No order")
    private class NoOrderExcpetion extends RuntimeException {
        public NoOrderExcpetion(Long id) {
            super("No such order with id: "+id);
        }
    }
}
