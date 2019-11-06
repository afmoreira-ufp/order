package edu.ufp.esof.order.controllers;

import edu.ufp.esof.order.models.Client;
import edu.ufp.esof.order.repositories.ClientRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientRepo clientRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Client>> getAllClients(){
        this.logger.info("Received a get request");

        return ResponseEntity.ok(this.clientRepo.findAll());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) throws NoClientExcpetion {
        this.logger.info("Received a get request");

        Optional<Client> optionalClient=this.clientRepo.findById(id);
        if(optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient.get());
        }
        throw new NoClientExcpetion(id);
    }


    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such client")
    class NoClientExcpetion extends RuntimeException {

        public NoClientExcpetion(Long id) {
            super("No such client with id: "+id);
        }
    }

}