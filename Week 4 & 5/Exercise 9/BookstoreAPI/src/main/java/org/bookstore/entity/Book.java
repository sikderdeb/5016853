package org.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, message = "Title should have at least 2 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    @Size(min = 2, message = "Author should have at least 2 characters")
    private String author;

    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price should be greater than 0")
    private Integer price;

    @NotNull(message = "ISBN cannot be null")
    private Long isbn;

    @Version
    private Integer version; // Used for optimistic locking
}
