package dev.SAlmeidaJr.StockManager.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemDto(
    @NotNull UUID productId,
    @NotNull @Positive Integer quantity
){}
