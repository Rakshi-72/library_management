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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.config.ApiResponse;
import org.training.library_management.dtos.BookDto;
import org.training.library_management.services.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService service;

    /**
     * It takes a BookDto object as a parameter, calls the addBook function in the
     * service layer, and
     * returns the result of that function as a response
     * 
     * @param dto BookDto object
     * @return A ResponseEntity object with a BookDto object as a parameter.
     */
    @PostMapping("/add")
    @ApiOperation(value = "add book to library", notes = "It takes a BookDto object as a parameter, calls the addBook function in the service layer, and returns the result of that function as a response", response = BookDto.class)
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto dto) {
        return new ResponseEntity<>(this.service.addBook(dto), HttpStatus.CREATED);
    }

    /**
     * It deletes a book from the database
     * 
     * @param id The id of the book to be deleted
     * @return A ResponseEntity object is being returned.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Integer id) {
        this.service.deleteBook(id);
        return new ResponseEntity<>(new ApiResponse("book deleted", true), HttpStatus.OK);
    }

    /**
     * This function returns a list of all books in the database
     * 
     * @return A list of BookDto objects
     */
    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(this.service.getAllBooks(), HttpStatus.OK);
    }

    /**
     * It returns a ResponseEntity of type BookDto, which is a DTO (Data Transfer
     * Object) that contains
     * the data of a book
     * 
     * @param id The id of the book to be retrieved
     * @return A ResponseEntity object with a BookDto object and a HttpStatus.FOUND
     *         status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Integer id) {
        return new ResponseEntity<>(this.service.getBookById(id), HttpStatus.OK);
    }

    /**
     * It takes an id and a bookDto, and returns an updated bookDto
     * 
     * @param id  The id of the book to be updated
     * @param dto This is the object that will be used to update the book.
     * @return A ResponseEntity with a BookDto and a HttpStatus.CREATED
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Integer id, @Valid @RequestBody BookDto dto) {
        return new ResponseEntity<>(this.service.updateBook(id, dto), HttpStatus.CREATED);
    }

    /**
     * It takes a string as a parameter, and returns a list of BookDto objects
     * 
     * @param key the key to search for
     * @return A list of BookDto objects
     */
    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchBook(@RequestParam(value = "key") String key) {
        return new ResponseEntity<>(this.service.searchBooksByKey(key), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAllAvailableBooks() {
        return new ResponseEntity<>(this.service.getAvailableBooks(), HttpStatus.OK);
    }

}
