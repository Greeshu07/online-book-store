package com.bookstore.user_service.service;

import com.bookstore.user_service.dto.BookDTO;

import com.bookstore.user_service.dto.OrderDTO;
import com.bookstore.user_service.model.User;
import com.bookstore.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${book.service.url}")
    private String bookServiceUrl;
    
    @Value("${order.service.url}")
    private String orderServiceUrl;

    // Create/Register a new user
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get user by ID
    @Override
    public User getUserById(Long id) {
        User user = userRepository.getUserById(id); // assuming this returns null if not found
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
    
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Credentials match
        }
        return false; // Invalid credentials
    } 

    // Get all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Place The Oder
    @Override
	public OrderDTO placeOrder(OrderDTO orderDTO)  {
		return restTemplate.postForObject(orderServiceUrl + "/orders", orderDTO, OrderDTO.class);
    	
    }
    
    //Get the User Orders
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        ResponseEntity<List<OrderDTO>> response = restTemplate.exchange(
        	orderServiceUrl+"/orders/user/" + userId,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<OrderDTO>>() {}
        );

        return response.getBody();
    }
    
    //Get The Books
    @Override
    public List<BookDTO> getAllBooks() {
        // Make GET request to Book Service to get all books
        ResponseEntity<List<BookDTO>> response = restTemplate.exchange(
        		bookServiceUrl+"/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDTO>>() {}
        );
        return response.getBody();
    }

    
    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
    
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.delete(user);
    }

}