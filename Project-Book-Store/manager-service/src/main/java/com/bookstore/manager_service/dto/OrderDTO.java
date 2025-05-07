package com.bookstore.manager_service.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private Integer quantity;

    // Getters and Setters
}
