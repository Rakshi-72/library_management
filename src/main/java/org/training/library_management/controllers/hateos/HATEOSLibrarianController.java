package org.training.library_management.controllers.hateos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.services.BookService;
import org.training.library_management.services.LibrarianService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/hateos")
@Validated
public class HATEOSLibrarianController {
    @Autowired
    private LibrarianService service;

    @Autowired
    private BookService bookService;

    /**
     * We are getting all the librarians from the database, and then for each librarian we are adding a link to the
     * librarian's details and a link to the librarian's borrowed books
     *
     * @return CollectionModel<LibrarianDtoResponse>
     */
    @GetMapping("/librarians")
    public CollectionModel<LibrarianDtoResponse> getAllUsers() {
        List<LibrarianDtoResponse> librarians = service.getAllLibrarians();
        librarians.stream().forEach(librarian -> {

            Link selfLink = linkTo(this.getClass()).slash(librarian.getId()).withSelfRel();
            librarian.add(selfLink);
            if (!librarian.getBooksBorrowed().isEmpty()) {
                Link booksLink = linkTo(methodOn(HATEOSBookController.class)
                        .getBorrowedBooks(librarian.getId())).withRel("books");
                librarian.add(booksLink);
            }
        });
        Link link = linkTo(this.getClass()).withSelfRel();
        CollectionModel<LibrarianDtoResponse> result = CollectionModel.of(librarians, link);
        return result;
    }

}
