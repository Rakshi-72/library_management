package org.training.library_management.exceptions;

public class BookAlreadyBorrowedBySomeOneException extends RuntimeException {

    public BookAlreadyBorrowedBySomeOneException(Integer id) {
        super(String.format("book with the given id : %d is already borrowed by someone", id));
    }

}
