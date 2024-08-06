package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public void getBooks() {
        System.out.println("Fetching books from the repository...");
    }
}
