package org.training.library_management.controllers.hateos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.controllers.BookController;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.services.LibrarianService;

@RestController
@RequestMapping("/api/hateos")
@Validated
public class HATEOSLibrarianController {
    @Autowired
    private LibrarianService service;

    /**
     * We are getting all the librarians from the database, and then for each
     * librarian we are adding a link to the
     * librarian's details and a link to the librarian's borrowed books
     *
     * @return CollectionModel<LibrarianDtoResponse>
     */
    @GetMapping("/librarians")
    public CollectionModel<LibrarianDtoResponse> getAllUsers(HttpServletResponse response) {
        List<LibrarianDtoResponse> librarians = service.getAllLibrarians();
        librarians.stream().forEach(librarian -> {

            Link selfLink = linkTo(this.getClass()).slash("users").slash(librarian.getId()).withSelfRel();
            librarian.add(selfLink);

            if (!librarian.getBooksBorrowed().isEmpty()) {
                Link booksLink = linkTo(methodOn(HATEOSBookController.class)
                        .getBorrowedBooks(librarian.getId())).withRel("books");
                librarian.add(booksLink);
            }
        });
        Link link = linkTo(this.getClass()).slash("librarians").withSelfRel();
        CollectionModel<LibrarianDtoResponse> result = CollectionModel.of(librarians, link);
        response.setStatus(HttpServletResponse.SC_OK);
        return result;
    }

    /**
     * The function returns a LibrarianDtoResponse object, which is a DTO (Data
     * Transfer Object) that
     * contains the information of a librarian. The function takes in an integer id,
     * which is the id of
     * the librarian. The function gets the librarian by id, and then adds a self
     * link to the
     * librarian. The function then adds a link to each book that the librarian has
     * borrowed
     * 
     * @param id the id of the librarian
     * @return A LibrarianDtoResponse object
     */
    @GetMapping("/librarians/{id}")
    public LibrarianDtoResponse getLibrarianById(@PathVariable Integer id) {
        var librarian = this.service.getLibrarianById(id);
        Link selfLink = linkTo(this.getClass()).slash("librarians").slash(librarian.getId()).withSelfRel();
        librarian.add(selfLink);

        librarian.getBooksBorrowed().forEach(book -> {
            Link link = linkTo(BookController.class).slash(book.getId()).withSelfRel();
            book.add(link);
        });
        return librarian;
    }

}
