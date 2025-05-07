package com.bookstore.user_service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderDTO {
	private Long id;
    private Long userId;
    private Long bookId;
    private int quantity;
    private double totalPrice;
    private String bookName;
    private String status;
    private LocalDateTime orderTime;

}
