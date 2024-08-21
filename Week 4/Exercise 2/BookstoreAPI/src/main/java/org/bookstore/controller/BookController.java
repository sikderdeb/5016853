package org.bookstore.controller;

import org.bookstore.entity.Book;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private final List<Book> books= new ArrayList<>();

    @GetMapping
    public List<Book> getBook() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") int id) {
        for (Book book : books) {
            if (book.getId() == id)
                return book;
        }
        return null;
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        for (Book book1 : books) {
            if (Objects.equals(book1.getId(), book.getId())) {
                return;
            }
        }
        books.add(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        Book oldBook = getBookById(id);
        if (oldBook != null) {
            if(book.getTitle() != null)
                oldBook.setTitle(book.getTitle());
            if(book.getAuthor() != null)
                oldBook.setAuthor(book.getAuthor());
            if(book.getPrice() != null)
                oldBook.setPrice(book.getPrice());
            if (book.getIsbn() != null)
                oldBook.setIsbn(book.getIsbn());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") int id) {
        int index = 0;
        while(index < books.size()) {
            if (books.get(index).getId() == id) {
                books.remove(index);
                break;
            }
            index++;
        }
    }
}