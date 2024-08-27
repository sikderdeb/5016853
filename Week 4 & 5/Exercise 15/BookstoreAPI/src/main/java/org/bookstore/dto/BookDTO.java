package org.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields in JSON
public class BookDTO {
    private Integer id;

    @JsonProperty("book_title") // Custom JSON property name
    private String title;

    private String author;
    private Integer price;

    @JsonProperty("isbn_number") // Custom JSON property name
    private String isbn;
}
