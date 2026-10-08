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
        if(customerRepo.existsByEmail(body.email())){
           throw new RuntimeException("Email already in use");
        }
        String hash = security.encode(body.password());
        CustomerDto newBody = new CustomerDto(
            body.name(),
            body.email(),
            hash
        );

        Customer customer = CustomerConverters.toCustomer(newBody);
        Customer savedCustomer = customerRepo.saveAndFlush(customer);

        customer.setPassword(null);
        return ResponseEntity.ok(savedCustomer);

    }

    public ResponseEntity<Customer> log(CustomerDto body){
        if(customerRepo.existsByEmail(body.email())){
            throw new RuntimeException("Email already in use");
        }
        Customer customerOnDb = customerRepo.findByEmail(body.email());

        if(security.matches(body.password(), customerOnDb.getPassword())){
            throw new RuntimeException("Password or Email do not matches");
        }
        customerOnDb.setPassword(null);
        return ResponseEntity.ok(customerOnDb);

    }
}
