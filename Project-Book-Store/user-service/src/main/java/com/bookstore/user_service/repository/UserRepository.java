package com.bookstore.user_service.repository;

import com.bookstore.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can define additional query methods if needed
	User findByUsername(String username);
	User getUserById(Long id);
}
