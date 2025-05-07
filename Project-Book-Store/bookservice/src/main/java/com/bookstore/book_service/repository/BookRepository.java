package com.bookstore.book_service.repository;

import java.util.Optional;
import com.bookstore.book_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findByTitle(String title);
	 Book findById(long id);
}
