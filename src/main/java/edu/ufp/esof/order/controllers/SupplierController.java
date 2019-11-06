package edu.ufp.esof.order.controllers;

import edu.ufp.esof.order.models.Supplier;
import edu.ufp.esof.order.repositories.ClientRepo;
import edu.ufp.esof.order.repositories.SupplierRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SupplierRepo supplierRepo;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Supplier>> getAllSuppliers(){
        return ResponseEntity.ok(this.supplierRepo.findAll());
    }
}
