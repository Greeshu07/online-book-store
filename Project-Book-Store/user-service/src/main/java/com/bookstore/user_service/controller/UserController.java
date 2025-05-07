package com.bookstore.user_service.controller;

import com.bookstore.user_service.dto.BookDTO;
import com.bookstore.user_service.dto.OrderDTO;
import com.bookstore.user_service.model.User;
import com.bookstore.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
  
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticateUser(user.getUsername(), user.getPassword());

        return isAuthenticated ? "Login Successful" : "Invalid Credentials";
    }
    
    @PostMapping("/{userId}/order")
    public OrderDTO placeOrder(@PathVariable Long userId, @RequestBody OrderDTO orderDTO) {
        orderDTO.setUserId(userId); 
        return userService.placeOrder(orderDTO);
    }
    
    
    // Get user details by ID
    @GetMapping("id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    // Get user details by Username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserProfile(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/{id}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable Long id) {
        try {
            List<?> orders = userService.getOrdersByUserId(id);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to fetch orders for user ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
    
    //Get Books
    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {
        return userService.getAllBooks();
    }


    // Get all users (for manager use)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    
    // Deleting The User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
