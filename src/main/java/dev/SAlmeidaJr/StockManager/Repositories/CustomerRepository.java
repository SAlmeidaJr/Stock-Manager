package dev.SAlmeidaJr.StockManager.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.SAlmeidaJr.StockManager.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

    Customer findByEmail(String email);
    boolean existsByEmail(String email);


}
