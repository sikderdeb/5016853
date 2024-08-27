package org.bookstore.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.bookstore.entity.Book;
import org.bookstore.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MeterRegistry meterRegistry;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetBooks() throws Exception {
        Book book1 = new Book(1, "Book One", "Author One", 20, 123456789L, 1);
        Book book2 = new Book(2, "Book Two", "Author Two", 25, 987654321L, 1);

        when(bookController.getBooks()).thenReturn(
                ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(CollectionModel.of(Arrays.asList(EntityModel.of(book1), EntityModel.of(book2)))));

        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.bookList", hasSize(2)));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1, "Book One", "Author One", 20, 123456789L, 1);

        when(bookController.getBookById(1)).thenReturn(
                ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(EntityModel.of(book)));

        mockMvc.perform(get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Book One"));
    }

    @Test
    public void testGetBookById_NotFound() throws Exception {
        when(bookController.getBookById(999)).thenThrow(new BookNotFoundException("Book with ID 999 not found"));

        mockMvc.perform(get("/books/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book with ID 999 not found"));
    }

    @Test
    public void testAddBook() throws Exception {
        String newBookJson = "{\"title\":\"New Book\",\"author\":\"New Author\",\"price\":30,\"isbn\":1234567890}";

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBookJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Custom-Header", "AddBookHeader"))
                .andExpect(content().string("Book added successfully"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(header().string("Custom-Header", "DeleteBookHeader"));
    }
}
