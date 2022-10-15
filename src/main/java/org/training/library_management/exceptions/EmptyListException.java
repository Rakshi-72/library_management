package org.training.library_management.exceptions;

public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("sorry couldn't find any");
    }
}
