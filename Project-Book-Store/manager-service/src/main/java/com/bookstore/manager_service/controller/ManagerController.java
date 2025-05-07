package com.bookstore.manager_service.controller;

import com.bookstore.manager_service.model.Manager;


import com.bookstore.manager_service.service.ManagerService;
import com.bookstore.manager_service.dto.BookDTO;
import com.bookstore.manager_service.dto.UserDTO;
import com.bookstore.manager_service.dto.OrderDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    
    @PostMapping("/register")
    public Manager createManager(@RequestBody Manager manager) {
        return managerService.createManager(manager);
    }

    @PostMapping("/login")
    public String login(@RequestBody Manager manager) {
        boolean isAuthenticated = managerService.authenticateManager(manager.getUsername(), manager.getPassword());

        return isAuthenticated ? "Login Successful" : "Invalid Credentials";
    }
   
    @PostMapping("/books")
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return managerService.addBook(bookDTO);
    }
    
    
    @GetMapping("/username/{username}")
    public Manager getManagerByUsername(@PathVariable String username) {
        return managerService.getManagerByUsername(username);
    }
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return managerService.getAllUsers();
    }

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return managerService.getAllOrders();
    }

    // Get all books
    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {
        return managerService.getAllBooks();
    }

    // Update book details
    @PutMapping("/books/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return managerService.updateBook(id, bookDTO);
    }
    
    @PatchMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBookFields(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = managerService.updateBookFields(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }


    // Delete a book
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        managerService.deleteBook(id);
    }
}
