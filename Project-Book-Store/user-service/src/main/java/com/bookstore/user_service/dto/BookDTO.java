package com.bookstore.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class BookDTO {
	
	private Long id;
	@JsonProperty("title")
    private String bookName;
    private String author;
    private Double price;
	

}
