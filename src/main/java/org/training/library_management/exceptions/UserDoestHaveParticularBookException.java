package org.training.library_management.exceptions;

public class UserDoestHaveParticularBookException extends RuntimeException {

    public UserDoestHaveParticularBookException(Integer id) {
        super(String.format("Given User doesn't have the particular book with the id : %d", id));
    }

}
