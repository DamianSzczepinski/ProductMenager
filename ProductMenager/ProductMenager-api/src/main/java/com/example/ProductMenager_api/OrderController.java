package com.example.ProductMenager_api;

import com.example.ProductMenager_data.entity.OrderEntity;
import com.example.ProductMenager_service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity order) {

        return orderService.save(order);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id,
                                                   @RequestBody OrderEntity newData) {
        return orderService.findById(id)
                .map(existing -> {
                    existing.setTotalPrice(newData.getTotalPrice());
                    existing.setOrderDate(newData.getOrderDate());
                    existing.setUser(newData.getUser());
                    // itp.
                    return ResponseEntity.ok(orderService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}