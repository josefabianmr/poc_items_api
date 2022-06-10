package com.samples.domain.exceptions;

/**
 * The item error.
 */
public class InvalidItemException extends RuntimeException {
    public InvalidItemException(final String message) {
        super(message);
    }
}
