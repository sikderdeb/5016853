package org.bookstore.mapper;

import javax.annotation.processing.Generated;
import org.bookstore.dto.BookDTO;
import org.bookstore.entity.Book;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T15:03:57+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setAuthor( book.getAuthor() );
        bookDTO.setPrice( book.getPrice() );
        if ( book.getIsbn() != null ) {
            bookDTO.setIsbn( String.valueOf( book.getIsbn() ) );
        }

        return bookDTO;
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setAuthor( bookDTO.getAuthor() );
        book.setPrice( bookDTO.getPrice() );
        if ( bookDTO.getIsbn() != null ) {
            book.setIsbn( Long.parseLong( bookDTO.getIsbn() ) );
        }

        return book;
    }
}
