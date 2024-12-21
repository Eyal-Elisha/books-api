package com.example.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom query to find books by title
    List<Book> findByTitle(String title);

    // Custom query to find books published after a certain date (keeping publishedDate as String)
    @Query("SELECT b FROM Book b WHERE b.publishedDate > :date")
    List<Book> findByPublishedDateAfter(@Param("date") String date);  // Keep as String for publishedDate
}
