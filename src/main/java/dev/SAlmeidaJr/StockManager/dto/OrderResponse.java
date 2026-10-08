package dev.SAlmeidaJr.StockManager.dto;


import java.util.List;

import dev.SAlmeidaJr.StockManager.models.Customer;
import dev.SAlmeidaJr.StockManager.models.Payment;
import dev.SAlmeidaJr.StockManager.models.Product;

public record OrderResponse(
    Customer customer,
    List<Product> items,
    Payment payment
){}
