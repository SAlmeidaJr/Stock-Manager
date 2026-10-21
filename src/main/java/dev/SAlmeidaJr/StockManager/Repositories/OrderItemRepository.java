package dev.SAlmeidaJr.StockManager.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.SAlmeidaJr.StockManager.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    OrderItem findByProduct_Name(String name);
}
