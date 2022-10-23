package org.training.library_management.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.library_management.dtos.BookDto;
import org.training.library_management.exceptions.BookNotFoundException;
import org.training.library_management.exceptions.EmptyListException;
import org.training.library_management.model.Book;
import org.training.library_management.repositories.BookRepo;
import org.training.library_management.services.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImplement implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ModelMapper mapper;

    /**
     * It takes a BookDto object, maps it to a Book object, saves the Book object to
     * the database, then
     * maps the Book object back to a BookDto object and returns it
     * 
     * @param dto BookDto
     * @return A BookDto object
     */
    @Override
    public BookDto addBook(BookDto dto) {
        Book book = this.bookRepo.save(mapper.map(dto, Book.class));
        log.info("adding book to library");
        return mapper.map(book, BookDto.class);
    }

    /**
     * This function takes an integer, finds a book in the database with that id,
     * and returns a BookDto
     * object that represents that book.
     * 
     * @param id The id of the book to be retrieved
     * @return A BookDto object
     */
    @Override
    public BookDto getBookById(Integer id) {
        log.info(String.format("getting book from the library with the id %d", id));
        return mapper.map(this.bookRepo.findById(id).orElseThrow(() -> {
            log.error("there is no book with the id " + id);
            return new BookNotFoundException(id);
        }), BookDto.class);
    }

    /**
     * Get all books from the database, convert them to BookDto objects, and return
     * them as a list.
     * 
     * @return A list of BookDto objects.
     */
    @Override
    public List<BookDto> getAllBooks() {
        log.info("getting all the books from the library");
        return this.bookRepo.findAll().stream().map(book -> mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    /**
     * It takes an id and a BookDto object as input, finds the book with the given
     * id, updates the book
     * with the values from the BookDto object, and returns the updated book as a
     * BookDto object
     * 
     * @param id  The id of the book to be updated
     * @param dto BookDto
     * @return BookDto
     */
    @Override
    public BookDto updateBook(Integer id, BookDto dto) {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> {
            log.error("there is no book with the id " + id);
            return new BookNotFoundException(id);
        });

        log.info("updating given book in the library");
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setNoOfPages(dto.getNoOfPages());
        book.setPrice(dto.getPrice());
        book.setPublishedYear(dto.getPublishedYear());

        log.info("book updated successfully");
        return mapper.map(this.bookRepo.save(book), BookDto.class);
    }

    /**
     * If the book exists, delete it. If it doesn't, throw an exception
     * 
     * @param id The id of the book to be deleted
     */
    @Override
    public void deleteBook(Integer id) {
        if (this.bookRepo.existsById(id)) {
            this.bookRepo.deleteById(id);
            log.info("book deleted successfully");
        }

        else {
            log.error("there is no book with the given id to delete");
            throw new BookNotFoundException(id);
        }

    }

    /**
     * This function takes a string as input, searches the database for books with
     * names containing
     * that string, and returns a list of BookDto objects.
     * 
     * @param key the search key
     * @return A list of BookDto objects.
     */
    @Override
    public List<BookDto> searchBooksByKey(String key) {
        var listOfBooks = this.bookRepo.findByNameContaining(key).stream().map(e -> mapper.map(e, BookDto.class))
                .toList();
        if (!listOfBooks.isEmpty()) {
            log.info("books that contains " + key + " founded successfully");
            return listOfBooks;
        }

        else {
            log.error("there is no book in the database that contain's " + key);
            throw new EmptyListException();
        }
    }

    /**
     * It returns a list of books that are available for borrowing
     * 
     * @return A list of BookDto objects.
     */
    @Override
    public List<BookDto> getAvailableBooks() {
        List<Book> availableBooks = this.bookRepo.findAll()
                .stream().filter(book -> book.getLibrarian() == null).toList();

        if (availableBooks.isEmpty()) {
            log.error("there is no available books in the database");
            throw new EmptyListException();
        }

        List<BookDto> books = availableBooks.stream()
                .map(book -> mapper.map(book, BookDto.class)).toList();
        log.info("books fetched successfully");
        return books;
    }

}
