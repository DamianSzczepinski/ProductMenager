package com.example.ProductMenager_data.repository;

import com.example.ProductMenager_data.entity.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUser_Id(Long userId);
    List<OrderEntity> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}