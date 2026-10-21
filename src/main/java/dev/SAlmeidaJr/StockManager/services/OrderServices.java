package dev.SAlmeidaJr.StockManager.services;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.SAlmeidaJr.StockManager.Repositories.OrderRepository;
import dev.SAlmeidaJr.StockManager.Repositories.ProductRepository;
import dev.SAlmeidaJr.StockManager.dto.OrderRequest;
import dev.SAlmeidaJr.StockManager.dto.OrderResponse;
import dev.SAlmeidaJr.StockManager.models.Order;
import dev.SAlmeidaJr.StockManager.models.OrderItem;
import dev.SAlmeidaJr.StockManager.models.Product;
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

    public ResponseEntity<Order> createOrder(OrderRequest body){
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

            if(product.getQuantity() > item.quantity()){
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

        var orderResponse = new OrderResponse(
            customerId,
            body.items(),
            total,
            payment);

        return null;
    }
}
