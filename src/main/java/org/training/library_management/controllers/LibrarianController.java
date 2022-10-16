package org.training.library_management.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.config.ApiResponse;
import org.training.library_management.dtos.BookDto;
import org.training.library_management.dtos.BookDtoSimple;
import org.training.library_management.dtos.LibrarianDtoRequest;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.services.LibrarianService;

@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {
    @Autowired
    private LibrarianService service;

    /**
     * It takes a LibrarianDtoRequest object, validates it, and then passes it to
     * the service layer
     * 
     * @param request the request body
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/add")
    public ResponseEntity<LibrarianDtoResponse> addLibrarian(@Valid @RequestBody LibrarianDtoRequest request) {
        return new ResponseEntity<>(service.createLibrarian(request), HttpStatus.CREATED);
    }

    /**
     * This function takes a library id and a book id and returns a book dto simple.
     * this controller helps to barrow book from the database
     * 
     * @param lId    the id of the library
     * @param bookId the id of the book to be borrowed
     * @return A bookDtoSimple object
     */
    @GetMapping("/{lId}/barrow/{bookId}")
    public ResponseEntity<BookDtoSimple> barrowBook(@PathVariable Integer lId, @PathVariable Integer bookId) {
        return ResponseEntity.ok(service.barrowBook(lId, bookId));
    }

    /**
     * It takes a library id and a book id, and returns a book
     * this controller helps to return books to database
     * 
     * @param lId    The id of the library
     * @param bookId The id of the book to be returned
     * @return A bookDto object
     */
    @GetMapping("/{lId}/return/{bookId}")
    public ResponseEntity<BookDto> returnBook(@PathVariable Integer lId, @PathVariable Integer bookId) {
        return ResponseEntity.ok(service.returnBook(lId, bookId));
    }

    /**
     * It returns a list of all librarians in the database
     * 
     * @return A list of LibrarianDtoResponse objects.
     */
    @GetMapping("/all")
    public ResponseEntity<List<LibrarianDtoResponse>> getAllLibrarians() {
        return new ResponseEntity<>(service.getAllLibrarians(), HttpStatus.OK);
    }

    /**
     * It takes a librarianId as a path variable, and returns a LibrarianDtoResponse
     * object
     * 
     * @param librarianId The id of the librarian to be retrieved.
     * @return A ResponseEntity object is being returned.
     */
    @GetMapping("/{librarianId}")
    public ResponseEntity<LibrarianDtoResponse> getLibrarianById(@PathVariable Integer librarianId) {
        return new ResponseEntity<>(this.service.getLibrarianById(librarianId), HttpStatus.FOUND);
    }

    /**
     * It deletes a librarian from the database
     * 
     * @param id The id of the librarian to be deleted.
     * @return A ResponseEntity object is being returned.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteLibrarian(@PathVariable Integer id) {
        this.service.deleteLibrarian(id);
        return new ResponseEntity<>(new ApiResponse(
                "librarian deleted successfully", true), HttpStatus.OK);
    }

    /**
     * It takes an id and a request body, validates the request body, and then
     * returns a response
     * entity with the updated librarian
     * 
     * @param id      The id of the librarian to be updated
     * @param request LibrarianDtoRequest
     * @return A ResponseEntity with the updated LibrarianDtoResponse and a status
     *         of ACCEPTED.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<LibrarianDtoResponse> updateLibrarian(@PathVariable Integer id,
            @Valid @RequestBody LibrarianDtoRequest request) {
        return new ResponseEntity<>(this.service.updateLibrarian(id, request), HttpStatus.ACCEPTED);
    }

    /**
     * It returns a list of books that have been borrowed by a librarian
     * 
     * @param librarianId the id of the librarian
     * @return A list of books that have been borrowed by a librarian.
     */
    @GetMapping("/{librarianId}/books")
    public ResponseEntity<List<BookDto>> getAllBooksBorrowedByLibrarian(@PathVariable Integer librarianId) {

        return new ResponseEntity<>(this.service.getBorrowedBooks(librarianId), HttpStatus.OK);
    }

}
