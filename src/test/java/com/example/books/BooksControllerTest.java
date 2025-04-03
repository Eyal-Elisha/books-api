package com.example.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Book testBook;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll(); // Clear the DB before each test
        testBook = new Book(null, "Test Book", "John Doe", "1234567890", "2024-01-01");
        bookRepository.save(testBook);
    }

    /*@Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Test Book")));
    }*/

   /* @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/api/books/" + testBook.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Book")));
    }*/

   /* @Test
    void testCreateBook() throws Exception {
        Book newBook = new Book(null, "New Book", "Jane Doe", "0987654321", "2024-02-01");

        String response = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("New Book")))
                .andReturn()
                .getResponse()
                .getContentAsString();


        Book createdBook = objectMapper.readValue(response, Book.class);


        Optional<Book> savedBook = bookRepository.findById(createdBook.getId());
        assert (savedBook.isPresent());
        assert (savedBook.get().getTitle().equals("New Book"));
    }*/


   /* @Test
    void testUpdateBook() throws Exception {
        Book updatedBook = new Book(null, "Updated Book", "Jane Doe", "1234567890", "2024-03-01");

        mockMvc.perform(put("/api/books/" + testBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Book")));


        Optional<Book> savedBook = bookRepository.findById(testBook.getId());
        assert (savedBook.isPresent());
        assert (savedBook.get().getTitle().equals("Updated Book"));
    }*/

/*
    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/" + testBook.getId()))
                .andExpect(status().isNoContent());

        Optional<Book> deletedBook = bookRepository.findById(testBook.getId());
        assert (deletedBook.isEmpty());
    }*/
@Test
void testGetBookById_NotFound() throws Exception {
    mockMvc.perform(get("/api/books/9999"))  // ID 9999 doesn't exist
            .andExpect(status().isNotFound());
}


}
