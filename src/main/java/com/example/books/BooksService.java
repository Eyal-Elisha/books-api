package com.example.books;

import com.example.books.errorHandler.BookNotFoundException;
import com.example.books.errorHandler.DuplicateIsbnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    public Book addBook(Book book) {
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            throw new DuplicateIsbnException(book.getIsbn());
        }
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (updatedBook.getTitle() != null) existingBook.setTitle(updatedBook.getTitle());
        if (updatedBook.getAuthor() != null) existingBook.setAuthor(updatedBook.getAuthor());
        if (updatedBook.getPublishedDate() != null) existingBook.setPublishedDate(updatedBook.getPublishedDate());

        if (updatedBook.getIsbn() != null &&
                !existingBook.getIsbn().equals(updatedBook.getIsbn()) &&
                bookRepository.findByIsbn(updatedBook.getIsbn()).isPresent()) {
            throw new IllegalArgumentException("ISBN already exists: " + updatedBook.getIsbn());
        }
        existingBook.setIsbn(updatedBook.getIsbn());

        return bookRepository.save(existingBook);
    }

    @Transactional
    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return true;
    }
}