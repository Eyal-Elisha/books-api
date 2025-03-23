package com.example.books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class Controller {

    private final BooksService booksService;

    public Controller(BooksService booksService) {
        this.booksService = booksService;
    }

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = booksService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    // GET a single book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = booksService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(book);
    }

    // POST create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = booksService.addBook(book);
        if (createdBook == null) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(201).body(createdBook);
    }

    // PUT update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = booksService.updateBook(id, book);
        if (updatedBook == null) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(updatedBook);
    }

    // DELETE a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = booksService.deleteBook(id);
        if (!deleted) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.noContent().build();
    }
}
