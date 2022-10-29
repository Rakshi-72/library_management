package org.training.library_management.services;

import org.training.library_management.dtos.BookDto;
import org.training.library_management.dtos.BookResponse;

import java.util.List;

public interface BookService {
    BookDto addBook(BookDto dto);

    BookDto getBookById(Integer id);

    BookResponse getAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    BookDto updateBook(Integer id, BookDto dto);

    void deleteBook(Integer id);

    List<BookDto> searchBooksByKey(String key);

    List<BookDto> getAvailableBooks();

}
