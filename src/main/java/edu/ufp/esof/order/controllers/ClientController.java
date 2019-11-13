package edu.ufp.esof.order.controllers;

import edu.ufp.esof.order.models.Client;
import edu.ufp.esof.order.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepo clientRepo;

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Client>> getAllClients(){
        return ResponseEntity.ok(this.clientRepo.findAll());
    }

}
