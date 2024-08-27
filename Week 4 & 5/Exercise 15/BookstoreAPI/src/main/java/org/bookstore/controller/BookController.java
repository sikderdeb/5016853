package org.bookstore.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.bookstore.entity.Book;
import org.bookstore.exception.BookNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Endpoints for managing books in the bookstore")
public class BookController {
    private final List<Book> books = new ArrayList<>();
    private final MeterRegistry meterRegistry;

    @Autowired
    public BookController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    // Get all books with a custom header
    @Operation(summary = "Get all books", description = "Retrieve a list of all books")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No books found")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<CollectionModel<EntityModel<Book>>> getBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "GetBooksHeader");

        List<Book> bookList = books;

        // Create EntityModels for each book
        List<EntityModel<Book>> bookModels = bookList.stream()
                .map(book -> {
                    Link selfLink = linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel();
                    return EntityModel.of(book, selfLink);
                })
                .collect(Collectors.toList());

        // Create CollectionModel for the list of books
        Link selfLink = linkTo(methodOn(BookController.class).getBooks()).withSelfRel();
        CollectionModel<EntityModel<Book>> collectionModel = CollectionModel.of(bookModels, selfLink);

        return new ResponseEntity<>(collectionModel, headers, HttpStatus.OK);
    }

    // Get a book by ID with a custom header
    @Operation(summary = "Get book by ID", description = "Retrieve a book by its ID")
    @ApiResponse(responseCode = "200", description = "Book found successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Custom-Header", "GetBookByIdHeader");

                // Create a link to the current book resource
                Link selfLink = linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel();
                // Create a link to the collection of books
                Link booksLink = linkTo(methodOn(BookController.class).getBooks()).withRel("all-books");

                EntityModel<Book> bookModel = EntityModel.of(book, selfLink, booksLink);

                return new ResponseEntity<>(bookModel, headers, HttpStatus.OK);
            }
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");
    }

    // Add a new book with a custom header and response status
    @Operation(summary = "Add a new book", description = "Add a new book to the bookstore")
    @ApiResponse(responseCode = "201", description = "Book added successfully")
    @ApiResponse(responseCode = "400", description = "Invalid book data")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addBook(@Valid @RequestBody Book book) {
        for (Book book1 : books) {
            if (Objects.equals(book1.getId(), book.getId())) {
                return new ResponseEntity<>("Book already exists", HttpStatus.CONFLICT);
            }
        }
        books.add(book);
        meterRegistry.counter("books.added.count").increment();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "AddBookHeader");
        return new ResponseEntity<>("Book added successfully", headers, HttpStatus.CREATED);
    }

    // Update an existing book with optimistic locking
    @Operation(summary = "Update a book", description = "Update an existing book")
    @ApiResponse(responseCode = "200", description = "Book updated successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<String> updateBook(@Valid @RequestBody Book book, @PathVariable("id") int id) {
        EntityModel<Book> bookEntityModel = getBookById(id).getBody();
        Book oldBook = bookEntityModel != null ? bookEntityModel.getContent() : null;
        if (oldBook != null) {
            if (!Objects.equals(oldBook.getVersion(), book.getVersion())) {
                return new ResponseEntity<>("Book has been modified by another transaction", HttpStatus.CONFLICT);
            }
            oldBook.setTitle(book.getTitle());
            oldBook.setAuthor(book.getAuthor());
            oldBook.setPrice(book.getPrice());
            oldBook.setIsbn(book.getIsbn());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Custom-Header", "UpdateBookHeader");
            return new ResponseEntity<>("Book updated successfully", headers, HttpStatus.OK);
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");
    }

    // Delete a book with a custom header and response status
    @Operation(summary = "Delete a book", description = "Delete a book by its ID")
    @ApiResponse(responseCode = "202", description = "Book deleted successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
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
