package com.bookstore.manager_service.service;

import com.bookstore.manager_service.model.Manager;
import com.bookstore.manager_service.dto.BookDTO;
import com.bookstore.manager_service.dto.OrderDTO;
import com.bookstore.manager_service.dto.UserDTO;
import java.util.List;

public interface ManagerService {
	Manager createManager(Manager manager);
    boolean authenticateManager(String username, String password);
    Manager getManagerByUsername(String username);
    BookDTO addBook(BookDTO bookDTO);       
    BookDTO updateBook(Long id, BookDTO bookDTO);  
    void deleteBook(Long id);               
    List<BookDTO> getAllBooks(); 
    BookDTO updateBookFields(Long bookId, BookDTO bookDTO);
    List<UserDTO> getAllUsers();
    List<OrderDTO> getAllOrders();
    
}
