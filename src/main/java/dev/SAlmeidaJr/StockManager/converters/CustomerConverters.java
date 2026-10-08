package dev.SAlmeidaJr.StockManager.converters;

import org.springframework.stereotype.Component;

import dev.SAlmeidaJr.StockManager.dto.CustomerDto;
import dev.SAlmeidaJr.StockManager.models.Customer;

@Component
public class CustomerConverters {

    public static Customer toCustomer(CustomerDto dto){
        return new Customer(
            dto.name(),
            dto.email(),
            dto.password()
            );
    }
}
