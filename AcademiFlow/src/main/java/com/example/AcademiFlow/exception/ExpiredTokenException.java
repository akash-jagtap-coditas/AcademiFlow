package com.example.AcademiFlow.exception;

/**
 * Exception thrown when a token has expired.
 */
public class ExpiredTokenException extends RuntimeException {

    /**
     * Constructs a new ExpiredTokenException with the specified detail message.
     *
     * @param message the detail message
     */
    public ExpiredTokenException(String message) {
        super(message);
    }
}
