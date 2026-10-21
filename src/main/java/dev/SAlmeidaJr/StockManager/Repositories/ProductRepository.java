package dev.SAlmeidaJr.StockManager.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.SAlmeidaJr.StockManager.models.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{

    Product findByNameLike(String name);
}
