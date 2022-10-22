package org.training.library_management.services.implement;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.library_management.dtos.BookDto;
import org.training.library_management.dtos.BookDtoSimple;
import org.training.library_management.dtos.LibrarianDtoRequest;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.exceptions.BookAlreadyBorrowedBySomeOneException;
import org.training.library_management.exceptions.BookNotFoundException;
import org.training.library_management.exceptions.LibrarianNotFoundException;
import org.training.library_management.exceptions.NoBookBorrowedYetException;
import org.training.library_management.exceptions.UserDoestHaveParticularBookException;
import org.training.library_management.model.Book;
import org.training.library_management.model.Librarian;
import org.training.library_management.repositories.BookRepo;
import org.training.library_management.repositories.LibrarianRepo;
import org.training.library_management.services.LibrarianService;

@Service
public class LibrarianServiceImplement implements LibrarianService {

    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ModelMapper mapper;

    /**
     * The function takes a LibrarianDtoRequest object, maps it to a Librarian
     * object, saves it to the database, and then
     * maps it back to a LibrarianDtoResponse object
     *
     * @param request The request object that is passed in from the controller.
     * @return LibrarianDtoResponse
     */
    @Override
    public LibrarianDtoResponse createLibrarian(LibrarianDtoRequest request) {
        Librarian savedLibrarian = this.librarianRepo.save(mapper.map(request, Librarian.class));
        return mapper.map(savedLibrarian, LibrarianDtoResponse.class);
    }

    /**
     * It returns a list of all librarians.
     *
     * @return A list of LibrarianDtoResponse objects.
     */
    @Override
    public List<LibrarianDtoResponse> getAllLibrarians() {
        return this.librarianRepo.findAll().stream().map(librarian -> mapper.map(librarian, LibrarianDtoResponse.class))
                .toList();
    }

    /**
     * > This function returns a LibrarianDtoResponse object that is mapped from a
     * Librarian object that is found by the id
     * that is passed in as a parameter
     *
     * @param id The id of the librarian to be retrieved.
     * @return LibrarianDtoResponse
     */
    @Override
    public LibrarianDtoResponse getLibrarianById(Integer id) {
        Librarian librarian = this.librarianRepo.findById(id).orElseThrow(() -> new LibrarianNotFoundException(id.toString()));
        return mapper.map(librarian, LibrarianDtoResponse.class);
    }

    /**
     * It takes in an id and a request object, finds the librarian with the given
     * id, updates the librarian's fields with
     * the request object's fields, saves the librarian, and returns the updated
     * librarian
     *
     * @param id      The id of the librarian to be updated.
     * @param request The request body of the request.
     * @return LibrarianDtoResponse
     */
    @Override
    public LibrarianDtoResponse updateLibrarian(Integer id, LibrarianDtoRequest request) {
        Librarian librarian = this.librarianRepo.findById(id).orElseThrow(() -> new LibrarianNotFoundException(id.toString()));

        librarian.setName(request.getName());
        librarian.setAge(request.getAge());
        librarian.setEmail(request.getEmail());
        librarian.setPassword(request.getPassword());
        librarian.setGender(request.getGender());

        Librarian savedLibrarian = this.librarianRepo.save(librarian);
        return mapper.map(savedLibrarian, LibrarianDtoResponse.class);
    }

    /**
     * If the librarian exists, delete it
     *
     * @param id The id of the librarian to be deleted.
     */
    @Override
    public void deleteLibrarian(Integer id) {
        if (this.librarianRepo.existsById(id))
            this.librarianRepo.deleteById(id);
        else
            throw new LibrarianNotFoundException(id.toString());
    }

    /**
     * It takes a bookId and a librarianId, finds the book and the librarian, checks
     * if the book is
     * already borrowed, if not, it sets the librarian to the book and saves it
     * 
     * @param librarianId 1
     * @param bookId      1
     * @return BookDtoSimple mappedBookDto
     */
    @Override
    public BookDtoSimple barrowBook(Integer librarianId, Integer bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        Librarian librarian = librarianRepo.findById(librarianId)
                .orElseThrow(() -> new LibrarianNotFoundException(librarianId.toString()));
        if (book.getLibrarian() != null)
            throw new BookAlreadyBorrowedBySomeOneException(bookId);
        book.setLibrarian(librarian);

        Book savedBook = bookRepo.save(book);

        BookDtoSimple mappedBookDto = mapper.map(savedBook, BookDtoSimple.class);
        mappedBookDto.setLibrarian(mapper.map(librarian, LibrarianDtoResponse.class));
        return mappedBookDto;
    }

    /**
     * "If the book is assigned to the librarian, then unassign it, otherwise throw
     * an exception."
     * 
     * The above function is a good example of a function that is hard to test
     * 
     * @param librarianId
     * @param bookId
     * @return BookDto
     */
    @Override
    public BookDto returnBook(Integer librarianId, Integer bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        Librarian librarian = librarianRepo.findById(librarianId)
                .orElseThrow(() -> new LibrarianNotFoundException(librarianId.toString()));

        if (book.getLibrarian() == librarian) {
            book.setLibrarian(null);
            bookRepo.save(book);
        } else {
            throw new UserDoestHaveParticularBookException(bookId);
        }
        return mapper.map(book, BookDto.class);
    }

    /**
     * It returns a list of books borrowed by a librarian
     * 
     * @param librarianId Integer
     * @return A list of BookDto objects.
     */
    @Override
    public List<BookDto> getBorrowedBooks(Integer librarianId) {
        Librarian librarian = librarianRepo.findById(librarianId)
                .orElseThrow(() -> new LibrarianNotFoundException(librarianId.toString()));

        List<Book> books = bookRepo.findByLibrarian(librarian);
        if (books.isEmpty())
            throw new NoBookBorrowedYetException(librarianId);

        return books.stream().map(book -> mapper.map(book, BookDto.class)).toList();
    }
}
