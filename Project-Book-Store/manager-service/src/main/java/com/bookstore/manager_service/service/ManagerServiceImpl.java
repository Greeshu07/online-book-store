package com.bookstore.manager_service.service;

import com.bookstore.manager_service.model.Manager;

import com.bookstore.manager_service.repository.ManagerRepository;
import com.bookstore.manager_service.dto.BookDTO;
import com.bookstore.manager_service.dto.OrderDTO;
import com.bookstore.manager_service.dto.UserDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@Service
public class ManagerServiceImpl implements ManagerService {
	
	 private final RestTemplate restTemplate;
	    
	    // Hardcoded Book Service URL
	    private final String bookServiceUrl = "http://localhost:8083";

	    public ManagerServiceImpl(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

    @Autowired
    private ManagerRepository managerRepository;
    
    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager); // Save the manager in the database
    }

    @Override
    public boolean authenticateManager(String username, String password) {
        Manager manager = managerRepository.findByUsername(username);
        if (manager != null && manager.getPassword().equals(password)) {
            return true; // Credentials are valid
        }
        return false; // Invalid credentials
    }
    
    @Override
    public Manager getManagerByUsername(String username) {
        Manager manager = managerRepository.findByUsername(username);
        if (manager == null) {
            throw new RuntimeException("Manager not found with username: " + username);
        }
        return manager;
    }
    
    @Override
    public List<BookDTO> getAllBooks() {
        // Make GET request to Book Service to get all books
        ResponseEntity<List<BookDTO>> response = restTemplate.exchange(
                bookServiceUrl + "/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>() {}
        );
        return response.getBody();
    }
    @Override
    public List<UserDTO> getAllUsers() {
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
            "http://localhost:8082/users", // Replace with user service URL
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<UserDTO>>() {});
        return response.getBody();
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(
            "http://localhost:8086/orders", // Replace with order service URL
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<OrderDTO>>() {});
        return response.getBody();
    }
    
    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        // Make POST request to Book Service to add a new book
        return restTemplate.postForObject(
                bookServiceUrl + "/books", bookDTO, BookDTO.class);
    }
    
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        // Make PUT request to Book Service to update book details
        restTemplate.put(bookServiceUrl + "/books/" + id, bookDTO);
        return bookDTO;
    }
    
    @Override
    public BookDTO updateBookFields(Long id, BookDTO bookDTO) {
        // Create HttpEntity with the DTO
        HttpEntity<BookDTO> requestEntity = new HttpEntity<>(bookDTO);

        // Perform PATCH request to Book Service
        ResponseEntity<BookDTO> response = restTemplate.exchange(
            bookServiceUrl + "/books/" + id,
            HttpMethod.PATCH,
            requestEntity,
            BookDTO.class
        );

        return response.getBody();
    }

    
    @Override
    public void deleteBook(Long id) {
        // Make DELETE request to Book Service to delete a book
        restTemplate.delete(bookServiceUrl + "/books/" + id);
    }
}
