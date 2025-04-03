package com.example.books.errorHandler;

public class DuplicateIsbnException extends RuntimeException {
    public DuplicateIsbnException(String isbn) {
        super("Book with ISBN " + isbn + " already exists (409)");
    }
}
