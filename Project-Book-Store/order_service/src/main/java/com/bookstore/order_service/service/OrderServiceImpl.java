package com.bookstore.order_service.service;

import com.bookstore.order_service.dto.BookDTO;


import com.bookstore.order_service.model.Order;
import com.bookstore.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Order placeOrder(Order order) {
        // Fetch book info from Book Service
        BookDTO book = restTemplate.getForObject(
            "http://localhost:8083/books/" + order.getBookId(),
            BookDTO.class
        );
         
        // Calculate total price
        double totalPrice = book.getPrice() * order.getQuantity();
        order.setTotalPrice(totalPrice);
        order.setOrderTime(LocalDateTime.now());
        order.setBookName(book.getBookName());
        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    

    @Override
    public Order getOrderById(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id).get();
        } else {
            return null;
        }
    }


    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        orderRepository.save(order);
    }
}
