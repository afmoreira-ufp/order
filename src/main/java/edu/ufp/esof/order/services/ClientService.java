package edu.ufp.esof.order.services;


import edu.ufp.esof.order.models.Client;
import edu.ufp.esof.order.repositories.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;


    public Iterable<Client> findAll() {
        return this.clientRepo.findAll();
    }

    public Optional<Client> findById(Long id) {
        return this.clientRepo.findById(id);
    }

    public Optional<Client> createClient(Client client) {
        Optional<Client> optionalClient=this.clientRepo.findByName(client.getName());
        if(optionalClient.isPresent()){
          return Optional.empty();
        }
        Client createdClient=this.clientRepo.save(client);
        return Optional.of(createdClient);
    }

    public Optional<Client> findByName(String name) {
        return this.clientRepo.findByName(name);
    }
}
