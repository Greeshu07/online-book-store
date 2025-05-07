package com.bookstore.order_service.controller;

import com.bookstore.order_service.model.Order;

import com.bookstore.order_service.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }
    

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return order; // This will return null if not found
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping
    public List<Order> getAlOrders() {
        return orderService.getAllOrders();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok("Order status updated to " + status);
    }

}
