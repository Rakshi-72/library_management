package org.training.library_management.controllers.hateos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.dtos.BookDto;
import org.training.library_management.services.LibrarianService;

@RestController
@RequestMapping("/api/hateos")
public class HATEOSBookController {

    @Autowired
    private LibrarianService service;

    @GetMapping("/borrowed/books/{id}")
    @Validated
    public List<BookDto> getBorrowedBooks(@PathVariable Integer id) {
        List<BookDto> books = service.getBorrowedBooks(id);
        books.stream().forEach(book -> {
            Link link = WebMvcLinkBuilder.linkTo(this.getClass()).slash(book.getId()).withSelfRel();
            book.add(link);
        });
        return books;
    }
}
