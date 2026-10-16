package dev.SAlmeidaJr.StockManager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    ResponseEntity<Customer> loginAccount(@RequestBody CustomerDto body){
        return customerServices.log(body);
    }

    @PutMapping
    ResponseEntity<Customer> updateAccount(@RequestBody CustomerDto body){
        return customerServices.update(body);
    }

    @GetMapping("/{email}")
    Customer getCustomerAccount (@RequestBody String email){
        return customerServices.getCustomer(email);
    }

    @DeleteMapping("/{email}")
    void deleteCustomerAccount(@RequestBody String email){
        customerServices.deleteCustomer(email);
    }

}
