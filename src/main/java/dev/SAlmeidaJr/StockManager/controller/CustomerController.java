package dev.SAlmeidaJr.StockManager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.SAlmeidaJr.StockManager.dto.CustomerDto;
import dev.SAlmeidaJr.StockManager.models.Customer;
import dev.SAlmeidaJr.StockManager.services.CustomerServices;

@RestController
public class CustomerController {

    private CustomerServices customerServices;

    CustomerController(CustomerServices customerServices){
        this.customerServices = customerServices;
    }

    @PostMapping("/save")
    ResponseEntity<Customer> logAccount(@RequestBody CustomerDto body){
        return customerServices.save(body);
    }

    @PostMapping("/login")
    ResponseEntity<Customer> login(@RequestBody CustomerDto body){
        return customerServices.log(body);
    }

}
