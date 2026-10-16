package dev.SAlmeidaJr.StockManager.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.SAlmeidaJr.StockManager.Repositories.CustomerRepository;
import dev.SAlmeidaJr.StockManager.converters.CustomerConverters;
import dev.SAlmeidaJr.StockManager.dto.CustomerDto;
import dev.SAlmeidaJr.StockManager.models.Customer;

@Service
public class CustomerServices {
    private static final Argon2PasswordEncoder security = new Argon2PasswordEncoder(16, 32, 1, 6000, 10);
    private final CustomerRepository customerRepo;

    CustomerServices(CustomerRepository customerRepo){
        this.customerRepo = customerRepo;
    }


    public ResponseEntity<Customer> save(CustomerDto body){
        if(!customerRepo.existsByEmail(body.email())){
           throw new RuntimeException("Email already in use");
        }
        var hash = security.encode(body.password());
        var newBody = new CustomerDto(
            body.name(),
            body.email(),
            hash,
            body.phoneNumber()
        );

        var customer = CustomerConverters.toCustomer(newBody);
        var savedCustomer = customerRepo.saveAndFlush(customer);

        customer.setPassword(null);
        return ResponseEntity.ok(savedCustomer);

    }

    public ResponseEntity<Customer> log(CustomerDto body){
        if(customerRepo.existsByEmail(body.email())){
            throw new RuntimeException("Email already in use");
        }
        var customerOnDb = customerRepo.findByEmail(body.email());

        if(security.matches(body.password(), customerOnDb.getPassword())){
            throw new RuntimeException("Password or Email do not matches");
        }
        customerOnDb.setPassword(null);
        return ResponseEntity.ok(customerOnDb);

    }

    public ResponseEntity<Customer> update(CustomerDto body){
        if(!customerRepo.existsByEmail(body.email())){
            throw new RuntimeException("Email not found");
        }
        var customer = customerRepo.findByEmail(body.email());

        customer.setEmail(body.email());
        customer.setName(body.name());
        customer.setPassword(body.password());
        customer.setPhoneNumber(body.phoneNumber());

        var updatedCustomer = customerRepo.saveAndFlush(customer);

        updatedCustomer.setPassword(null);
        return ResponseEntity.ok(updatedCustomer);
    }



    public Customer getCustomer(String email){
        return customerRepo.findByEmail(email);
    }


    public void deleteCustomer(String email){
        if(!customerRepo.existsByEmail(email)){
            throw new RuntimeException("Email not found");
        }
        customerRepo.deleteByEmail(email);
    }
}
