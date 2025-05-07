package com.bookstore.order_service.service;

import com.bookstore.order_service.model.Order;
import java.util.List;

public interface OrderService {

    Order placeOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getOrdersByUserId(Long userId);

    List<Order> getAllOrders();

    void updateOrderStatus(Long id, String status);
}
