package com.example.books;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BookRepository bookRepository;

    @Autowired
    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return null;
    }

    public Optional<Book> getBookById(Long id) {
        return null;
    }

    public Book addBook(Book book) {
        return null;
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return null;
    }

    public void deleteBook(Long id) {
    }
}
