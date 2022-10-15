package org.training.library_management.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer id) {
        super("book with the given id: " + id + " not found");
    }
}
