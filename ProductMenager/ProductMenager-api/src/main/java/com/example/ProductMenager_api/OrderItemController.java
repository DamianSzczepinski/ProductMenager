package com.example.ProductMenager_api;

import com.example.ProductMenager_data.entity.OrderItem;
import com.example.ProductMenager_service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }


    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        return orderItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/order-items
    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.save(orderItem);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id,
                                                     @RequestBody OrderItem updated) {
        return orderItemService.findById(id)
                .map(existing -> {
                    existing.setQuantity(updated.getQuantity());
                    existing.setOrder(updated.getOrder());
                    existing.setProduct(updated.getProduct());
                    // itp.
                    return ResponseEntity.ok(orderItemService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/order-items/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
