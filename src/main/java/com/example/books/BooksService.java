package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return bookRepository.findAll();
    }


    public Book getBookById(Long id) {
        try {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                return book.get();
            } else {
                throw new IllegalArgumentException("Book with ID " + id + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving book with ID " + id, e);
        }
    }


    @Transactional
    public Book addBook(Book book) {
        try {
            if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
                throw new IllegalArgumentException("ISBN already exists: " + book.getIsbn());
            }
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("Error adding book", e);
        }
    }


    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        try {
            Optional<Book> existingBookOpt = bookRepository.findById(id);
            if (existingBookOpt.isEmpty()) {
                throw new IllegalArgumentException("Book with ID " + id + " not found");
            }

            Book existingBook = existingBookOpt.get();
            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getAuthor() != null) {
                existingBook.setAuthor(updatedBook.getAuthor());
            }
            if (updatedBook.getPublishedDate() != null) {
                existingBook.setPublishedDate(updatedBook.getPublishedDate());
            }
            if (updatedBook.getIsbn() != null) {
                if (!existingBook.getIsbn().equals(updatedBook.getIsbn()) &&
                        bookRepository.findByIsbn(updatedBook.getIsbn()).isPresent()) {
                    throw new IllegalArgumentException("ISBN already exists: " + updatedBook.getIsbn());
                }
                existingBook.setIsbn(updatedBook.getIsbn());
            }
            return bookRepository.save(existingBook);
        } catch (Exception e) {
            throw new RuntimeException("Error updating book with ID " + id, e);
        }
    }


    @Transactional
    public boolean deleteBook(Long id) {
        try {
            if (!bookRepository.existsById(id)) {
                return false;
            }
            bookRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting book with ID " + id, e);
        }
    }
}
