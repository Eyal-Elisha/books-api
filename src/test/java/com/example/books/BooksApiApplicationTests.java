package com.example.books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BooksApiApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testBookIdGeneration() {
		Book book = new Book();
		book.setTitle("Test Book Title");
		book.setAuthor("Test Author");

		Book savedBook = bookRepository.save(book);

		assertNotNull(savedBook.getId(), "Book ID should be generated");

		assertNotNull(savedBook.getAuthor(), "Book should have an author");
	}
}
