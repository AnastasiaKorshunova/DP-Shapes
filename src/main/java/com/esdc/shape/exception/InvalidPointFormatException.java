package com.esdc.shape.exception;

public class InvalidPointFormatException extends RuntimeException {
    public InvalidPointFormatException(String message) {
        super(message);
    }

    public InvalidPointFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
