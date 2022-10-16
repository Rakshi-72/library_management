package org.training.library_management.exceptions;

public class LibrarianNotFoundException extends RuntimeException {
    public LibrarianNotFoundException(Integer id) {
        super(String.format("librarian with the given id: %d not found", id));
    }
}
