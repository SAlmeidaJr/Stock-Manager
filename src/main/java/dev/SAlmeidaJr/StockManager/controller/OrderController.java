package dev.SAlmeidaJr.StockManager.controller;

import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.SAlmeidaJr.StockManager.services.OrderServices;

@RestController
class OrderController {

    private OrderServices orderServices;

    OrderController(OrderServices orderServices){
        this.orderServices = orderServices;
    }

    @PostMapping("/")
    ResponseEntity<Order> setOrder(){
        return null;
    }


}
