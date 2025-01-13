package com.example.ProductMenager_service;

import com.example.ProductMenager_data.entity.OrderEntity;
import com.example.ProductMenager_data.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public Optional<OrderEntity> findById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}