package dev.SAlmeidaJr.StockManager.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreateReferenceRequestDTO(
    UUID customerId,

    @DecimalMin(value = "0,01", message = "total amount must be greater tha zero")
    BigDecimal totalAmount,

    @NotNull
    PayerDto payer,

    @NotNull
    BackUrlsDto backUrls,

    @NotNull
    DeliveryAddressDto deliveryAddres,

    @NotNull
    String notificationUrl,

    @NotNull
    @Valid
    List<ItemDto> items
){

    public record PayerDto(
        String name,
        String email
    ){}


    public record BackUrlsDto(
        String pending,
        String success,
        String failure
    ){}


    public record DeliveryAddressDto(
        String zipCode,
        String streetName,
        String streetNumber,
        String city,
        String state,
        String neighborhood
    ){}

    public record ItemDto(
        String id,
        String title,
        Integer quantity,
        BigDecimal unitPrice
    ){}
}
