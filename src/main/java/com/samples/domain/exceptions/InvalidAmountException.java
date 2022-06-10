package com.samples.domain.exceptions;

/**
 * The amount error.
 */
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(final String message) {
        super(message);
    }
}
