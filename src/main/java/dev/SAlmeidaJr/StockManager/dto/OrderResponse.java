package dev.SAlmeidaJr.StockManager.dto;


import java.math.BigDecimal;
import java.util.List;

import dev.SAlmeidaJr.StockManager.models.Customer;
import dev.SAlmeidaJr.StockManager.models.Payment;

public record OrderResponse(
    Customer customer,
    List<OrderItemDto> items,
    BigDecimal total,
    Payment payment
){}
