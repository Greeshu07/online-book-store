package com.bookstore.manager_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.manager_service.model.Manager;



public interface ManagerRepository extends JpaRepository<Manager, Long> {

    // Find a manager by their username
    Manager findByUsername(String username);
}
