package com.bookstore.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long bookId;
    private Integer quantity;
    private Double totalPrice;
    private String bookName;

    @Column(nullable = false)
    private String status; // e.g., PLACED, CANCELLED, DELIVERED

    private LocalDateTime orderTime;
}
