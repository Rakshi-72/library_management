package org.training.library_management.services;

import java.util.List;

import org.training.library_management.dtos.BookDto;

public interface BookService {
    BookDto addBook(BookDto dto);

    BookDto getBookById(Integer id);

    List<BookDto> getAllBooks();

    BookDto updateBook(Integer id, BookDto dto);

    void deleteBook(Integer id);

    List<BookDto> searchBooksByKey(String key);

    List<BookDto> getAvailableBooks();

}
