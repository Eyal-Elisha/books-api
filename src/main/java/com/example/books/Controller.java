package com.example.books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class Controller {

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        // Logic here
        return ResponseEntity.ok().body(null);
    }

    // GET a single book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        // Logic here
        return ResponseEntity.ok().body(null);
    }

    // POST create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        // Logic here
        return ResponseEntity.status(201).body(null);
    }

    // PUT update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        // Logic here
        return ResponseEntity.ok().body(null);
    }

    // DELETE a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // Logic here
        return ResponseEntity.noContent().build();
    }
}
