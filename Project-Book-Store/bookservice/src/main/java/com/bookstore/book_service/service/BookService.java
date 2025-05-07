package com.bookstore.book_service.service;

import com.bookstore.book_service.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book getBookByTitle(String title);
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book updateBooks(Long id, Book updatedBook);
    
    //
    
}
