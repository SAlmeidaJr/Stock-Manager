package dev.SAlmeidaJr.StockManager.services;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.SAlmeidaJr.StockManager.Repositories.OrderRepository;
import dev.SAlmeidaJr.StockManager.Repositories.ProductRepository;
import dev.SAlmeidaJr.StockManager.dto.OrderItemDTO;
import dev.SAlmeidaJr.StockManager.dto.OrderRequestDTO;
import dev.SAlmeidaJr.StockManager.dto.OrderResponseDTO;
import dev.SAlmeidaJr.StockManager.models.OrderItem;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServices {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    OrderServices(
        OrderRepository orderRepository,
        ProductRepository productRepository
    ){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<OrderResponseDTO> createOrder(OrderRequestDTO body){
        var customerId = body.customerId();

        var total = BigDecimal.ZERO;

        for(var item: body.items()){
            var product = productRepository
            .findById(item.productId())
            .orElseThrow(()->
                new EntityNotFoundException("Produto não encontrado!"));

            if(product.getQuantity() <= 0){
                throw new RuntimeException("Estoque de "+product.getName()+" em falta");
            }

            if(product.getQuantity() < item.quantity()){
                throw new RuntimeException("Quantidade insuficiente de produtos para compra, quantidade restante: "+product.getQuantity());
            }

            BigDecimal productPrice = product.getUnitPrice();

            BigDecimal subtotal = productPrice.multiply(
                BigDecimal.valueOf(item.quantity()));

            var orderItem = new OrderItem();

            orderItem.setProduct(product);
            orderItem.setQuantity(item.quantity());
            orderItem.setSubtotal(subtotal);
            orderItem.setUnitPrice(productPrice);

            total = total.add(subtotal);
        }

        List<OrderItemDTO> newItemsList = body.items()
            .stream()
            .map(item -> new OrderItemDTO(
                item.productId(),
                item.quantity()
            )).toList();

        var orderResponse = new OrderResponseDTO(
            customerId,
            newItemsList,
            total,
            "Ok"
            );

        return ResponseEntity.ok(orderResponse);
    }
}
