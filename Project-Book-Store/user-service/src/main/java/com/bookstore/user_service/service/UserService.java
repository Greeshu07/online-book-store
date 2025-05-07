package com.bookstore.user_service.service;

import com.bookstore.user_service.dto.BookDTO;
import com.bookstore.user_service.dto.OrderDTO;
import com.bookstore.user_service.model.User;
import java.util.List;

public interface UserService {

    // Create/Register a new user
    User createUser(User user);

    // Get user by ID
    User getUserById(Long id);
    
    //Get user by Name
    User findByUsername(String username);
    
    //Get the user Orders
    public List<OrderDTO> getOrdersByUserId(Long userId);
    
    //Place Orders 
    OrderDTO placeOrder(OrderDTO orderDTO);

    // Get all users
    List<User> getAllUsers();
    
    //Get all Books
    public List<BookDTO> getAllBooks();
    
    //Delete the user
    void deleteUser(Long id);
    
    //LogIn
    boolean authenticateUser(String username, String password);
}
