package dev.SAlmeidaJr.StockManager.dto;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import dev.SAlmeidaJr.StockManager.models.Customer;

public record OrderResponseDTO(
    UUID customerId,
    List<OrderItemDTO> items,
    BigDecimal total,
    String payment
){}
