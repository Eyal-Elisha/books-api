package com.example.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title)")
    List<Book> findByTitleIgnoreCase(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.publishedDate > :date")
    List<Book> findByPublishedDateAfter(@Param("date") String date);

    List<Book> findByAuthor(String author);
}