package org.bookstore.controller;

import org.bookstore.entity.Book;
import org.bookstore.exception.BookNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();

    // Endpoint to get all books with a custom header
    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetBooksHeader");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    // Endpoint to get a book by ID with a custom header
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Custom-Header", "GetBookByIdHeader");
                return new ResponseEntity<>(book, headers, HttpStatus.OK);
            }
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");
    }

    // Endpoint to add a new book with a custom header and response status
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        for (Book book1 : books) {
            if (Objects.equals(book1.getId(), book.getId())) {
                return new ResponseEntity<>("Book already exists", HttpStatus.CONFLICT);
            }
        }
        books.add(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "AddBookHeader");
        return new ResponseEntity<>("Book added successfully", headers, HttpStatus.CREATED);
    }

    // Endpoint to update an existing book with a custom header and response status
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        Book oldBook = getBookById(id).getBody();
        if (oldBook != null) {
            if (book.getTitle() != null)
                oldBook.setTitle(book.getTitle());
            if (book.getAuthor() != null)
                oldBook.setAuthor(book.getAuthor());
            if (book.getPrice() != null)
                oldBook.setPrice(book.getPrice());
            if (book.getIsbn() != null)
                oldBook.setIsbn(book.getIsbn());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "UpdateBookHeader");
            return new ResponseEntity<>("Book updated successfully", headers, HttpStatus.OK);
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");
    }

    // Endpoint to delete a book with a custom header and response status
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
        int index = 0;
        while (index < books.size()) {
            if (books.get(index).getId() == id) {
                books.remove(index);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Custom-Header", "DeleteBookHeader");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
            index++;
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");
    }
}
