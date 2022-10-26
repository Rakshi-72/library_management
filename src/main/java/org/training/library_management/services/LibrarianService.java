package org.training.library_management.services;

import java.util.List;

import org.training.library_management.dtos.BookDto;
import org.training.library_management.dtos.BookDtoSimple;
import org.training.library_management.dtos.LibrarianDtoRequest;
import org.training.library_management.dtos.LibrarianDtoResponse;

public interface LibrarianService {
    LibrarianDtoResponse createLibrarian(LibrarianDtoRequest request);

    List<LibrarianDtoResponse> getAllLibrarians();

    LibrarianDtoResponse getLibrarianById(Integer id);

    LibrarianDtoResponse updateLibrarian(Integer id, LibrarianDtoRequest request);

    void deleteLibrarian(Integer id);

    BookDtoSimple barrowBook(Integer librarianId, Integer bookId);

    BookDto returnBook(Integer librarianId, Integer bookId);

    List<BookDto> getBorrowedBooks(Integer librarianId);

    List<BookDto> returnAllBooks(Integer librarianId);

}
