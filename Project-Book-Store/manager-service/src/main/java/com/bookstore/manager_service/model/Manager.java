	package com.bookstore.manager_service.model;
	
	import jakarta.persistence.*;
	import lombok.Data;
	
	@Entity
	@Table(name = "manager")
	@Data
	public class Manager {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    @Column(nullable = false, unique = true)
	    private String username;
	
	    @Column(nullable = false)
	    private String password;
	
	    
	}
