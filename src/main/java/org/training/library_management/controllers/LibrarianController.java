package org.training.library_management.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;
import org.training.library_management.config.ApiResponse;
import org.training.library_management.dtos.ApiWithHeaderResponse;
import org.training.library_management.dtos.BookDto;
import org.training.library_management.dtos.BookDtoSimple;
import org.training.library_management.dtos.LibrarianDtoRequest;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.services.LibrarianService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {
    @Autowired
    private LibrarianService service;

    /**
     * It takes a LibrarianDtoRequest object, validates it, and then passes it to
     * the service layer
     * 
     * @param request The request object that is passed to the controller.
     * @param builder This is a UriComponentsBuilder object that is used to build
     *                the URI of the newly
     *                created resource.
     * @return A ResponseEntity object with a LibrarianDtoResponse object and a
     *         HttpStatus.CREATED
     *         status.
     */
    @PostMapping("/add")
    @ApiOperation(value = "create a Librarian", notes = "It takes a LibrarianDtoRequest object, validates it, and then passes it to the service layer", response = LibrarianDtoResponse.class)
    public ResponseEntity<ApiWithHeaderResponse<LibrarianDtoResponse>> addLibrarian(
            @Valid @RequestBody LibrarianDtoRequest request,
            UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        LibrarianDtoResponse savedUser = service.createLibrarian(request);
        headers.setLocation(builder.path("/api/librarian/" + savedUser.getId()).buildAndExpand().toUri());
        return new ResponseEntity<>(new ApiWithHeaderResponse<>(savedUser, headers),
                HttpStatus.CREATED);
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
    @ApiOperation(value = "barrow book from library ", notes = "This function takes a library id and a book id and returns a book dto simple. this controller helps to barrow book from the database", response = BookDtoSimple.class)
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
    @ApiOperation(value = "return book to library ", notes = "It takes a library id and a book id, and returns a book. this controller helps to return books to database", response = BookDto.class)
    public ResponseEntity<BookDto> returnBook(@PathVariable Integer lId, @PathVariable Integer bookId) {
        return ResponseEntity.ok(service.returnBook(lId, bookId));
    }

    /**
     * It returns a list of all librarians in the database
     * 
     * @return A list of LibrarianDtoResponse objects.
     */
    @GetMapping("/all")
    @ApiOperation(value = "get all librarians ", notes = "It returns a list of all librarians in the database", response = LibrarianDtoResponse.class)
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
    @ApiOperation(value = "get librarian ", notes = "It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object", response = LibrarianDtoResponse.class)
    public ResponseEntity<LibrarianDtoResponse> getLibrarianById(@PathVariable Integer librarianId) {
        return new ResponseEntity<>(this.service.getLibrarianById(librarianId), HttpStatus.OK);
    }

    /**
     * It deletes a librarian from the database
     * 
     * @param id The id of the librarian to be deleted.
     * @return A ResponseEntity object is being returned.
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete librarian ", notes = "It deletes a librarian from the database", response = ApiResponse.class)
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
    @ApiOperation(value = "update librarian ", notes = "It takes an id and a request body, validates the request body, and then returns a response entity with the updated librarian", response = LibrarianDtoResponse.class)
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
    @ApiOperation(value = "get librarian borrowed books", notes = "It returns a list of books that have been borrowed by a librarian", response = BookDto.class)
    public ResponseEntity<List<BookDto>> getAllBooksBorrowedByLibrarian(@PathVariable Integer librarianId) {

        return new ResponseEntity<>(this.service.getBorrowedBooks(librarianId), HttpStatus.OK);
    }

    /**
     * It returns a list of books back to the library that have been borrowed by a
     * librarian
     * 
     * @param librarianId the id of the librarian who is returning the books
     * @return A list of books that have been returned.
     */
    @GetMapping("/{librarianId}/return/all")
    @ApiOperation(value = "return all the books borrowed", notes = "It returns a list of books back to the library that have been borrowed by a librarian", response = BookDtoSimple.class)
    public ResponseEntity<List<BookDto>> returnAllBorrowedBooks(@PathVariable Integer librarianId) {
        return new ResponseEntity<>(this.service.returnAllBooks(librarianId), HttpStatus.OK);
    }

}
