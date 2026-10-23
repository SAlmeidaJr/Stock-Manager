package dev.SAlmeidaJr.StockManager.services;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import dev.SAlmeidaJr.StockManager.Repositories.OrderRepository;
import dev.SAlmeidaJr.StockManager.dto.CreateReferenceRequestDTO;
import dev.SAlmeidaJr.StockManager.enums.PaymentStatus;

@Service
public class PaymentService {

    private final OrderRepository orderRepository;

    PaymentService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    void createTrasaction(CreateReferenceRequestDTO body){
    }
}
