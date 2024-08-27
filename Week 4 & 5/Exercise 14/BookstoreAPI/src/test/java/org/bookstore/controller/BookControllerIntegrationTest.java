package org.bookstore.controller;

import org.bookstore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Book testBook;

    @BeforeEach
    public void setUp() {
        testBook = new Book(null, "Test Title", "Test Author", 100, 1234567890123L, null);
    }

    @Test
    public void testAddBook() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Custom-Header", "AddBookHeader"))
                .andExpect(content().string("Book added successfully"));
    }

    @Test
    public void testGetBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(header().string("Custom-Header", "GetBooksHeader"))
                .andExpect(jsonPath("$._embedded.bookList[0].title").value("Test Title"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        testBook.setTitle("Updated Title");
        mockMvc.perform(put("/books/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isOk())
                .andExpect(header().string("Custom-Header", "UpdateBookHeader"))
                .andExpect(content().string("Book updated successfully"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", 1))
                .andExpect(status().isNoContent())
                .andExpect(header().string("Custom-Header", "DeleteBookHeader"));
    }
}
