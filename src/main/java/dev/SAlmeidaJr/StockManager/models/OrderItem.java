package dev.SAlmeidaJr.StockManager.models;

import java.math.BigDecimal;
import java.util.UUID;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer quantity;

    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_item_id", nullable = false)
    private Order order;

}
