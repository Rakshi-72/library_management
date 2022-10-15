package org.training.library_management.exceptions;

import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * It takes a MethodArgumentNotValidException, which is thrown by Spring when a
     * validation error
     * occurs, and returns a ResponseEntity with a Properties object containing the
     * field names and error
     * messages
     * 
     * @param exception The exception object that was thrown.
     * @return A Properties object.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Properties> validationExceptionHandler(MethodArgumentNotValidException exception) {
        Properties props = new Properties();
        exception.getBindingResult().getAllErrors()
                .forEach(e -> props.setProperty(((FieldError) e).getField(), e.getDefaultMessage()));
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    /**
     * It takes a BookNotFoundException and returns a ResponseEntity with a
     * Properties object
     * containing a message property
     * 
     * @param exception The exception object that was thrown.
     * @return A ResponseEntity object.
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Properties> bookNotFondExceptionHandler(BookNotFoundException exception) {
        Properties props = new Properties();
        props.setProperty("message", exception.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    /**
     * This function will be called whenever an EmptyListException is thrown. It
     * will return a response
     * with a status code of 400 and a message property with the exception message
     * 
     * @param exception The exception object that was thrown.
     * @return A ResponseEntity object.
     */
    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<Properties> emptyListExceptionHandler(EmptyListException exception) {
        Properties props = new Properties();
        props.setProperty("message", exception.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }
}
