package org.training.library_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Properties;

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

    /**
     * It takes an exception, creates a new Properties object, sets the message
     * property to the
     * exception's message, and returns a new ResponseEntity with the Properties
     * object and a 404
     * status code
     *
     * @param exception The exception object that was thrown.
     * @return A ResponseEntity object is being returned.
     */
    @ExceptionHandler(LibrarianNotFoundException.class)
    public ResponseEntity<Properties> handleLibrarianNotFoundException(LibrarianNotFoundException exception) {
        Properties props = new Properties();
        props.setProperty("message", exception.getMessage());
        return new ResponseEntity<>(props, HttpStatus.NOT_FOUND);
    }

    /**
     * It takes an exception, creates a new Properties object, sets the message
     * property to the
     * exception's message, and returns a new ResponseEntity with the Properties
     * object and a
     * HttpStatus.BAD_REQUEST
     *
     * @param exception The exception object that was thrown.
     * @return A ResponseEntity object is being returned.
     */
    @ExceptionHandler(UserDoestHaveParticularBookException.class)
    public ResponseEntity<Properties> handleParticularBookDoestHaveException(
            UserDoestHaveParticularBookException exception) {
        Properties props = new Properties();
        props.setProperty("message", exception.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    /**
     * It takes an exception, creates a new Properties object, sets the message
     * property to the
     * exception's message, and returns a new ResponseEntity with the Properties
     * object and a
     * BAD_REQUEST status
     *
     * @param exception The exception object that was thrown.
     * @return A ResponseEntity object is being returned.
     */
    @ExceptionHandler(BookAlreadyBorrowedBySomeOneException.class)
    public ResponseEntity<Properties> handleBookBorrow(BookAlreadyBorrowedBySomeOneException exception) {
        Properties props = new Properties();
        props.setProperty("message", exception.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    /**
     * It takes an exception, creates a new Properties object, sets the message
     * property to the
     * exception's message, and returns a new ResponseEntity with the Properties
     * object and a
     * BAD_REQUEST status
     *
     * @param exception The exception object that was thrown.
     * @return A ResponseEntity object is being returned.
     */
    @ExceptionHandler(NoBookBorrowedYetException.class)
    public ResponseEntity<Properties> handleBookBorrow(NoBookBorrowedYetException exception) {
        Properties props = new Properties();
        props.setProperty("message", exception.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Properties> handleUserNameNotFoundException(UsernameNotFoundException exception) {
        Properties props = new Properties();
        props.setProperty("message", exception.getMessage());
        return new ResponseEntity<>(props, HttpStatus.BAD_REQUEST);
    }

    /**
     * If the user is not authenticated, return a 401 Unauthorized response
     *
     * @param exception The exception that was thrown.
     * @return A ResponseEntity with a Properties object and a HttpStatus.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Properties> accessDenied(AccessDeniedException exception) {
        Properties props = new Properties();
        props.setProperty("message", "access denied");
        return new ResponseEntity<>(props, HttpStatus.UNAUTHORIZED);
    }
}
