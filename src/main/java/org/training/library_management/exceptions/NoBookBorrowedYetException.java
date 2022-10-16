package org.training.library_management.exceptions;

public class NoBookBorrowedYetException extends RuntimeException {

    public NoBookBorrowedYetException(Integer id) {
        super(String.format("Librarian with the given id :%d haven't borrowed any books yet", id));
    }

}
