package org.training.library_management.exceptions;

public class LibrarianNotFoundException extends RuntimeException {
    public LibrarianNotFoundException(String message) {
        super(String.format("librarian with the given id: %s not found", message));
    }
}
