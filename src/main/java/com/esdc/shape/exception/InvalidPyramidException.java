package com.esdc.shape.exception;

public class InvalidPyramidException extends RuntimeException {
    public InvalidPyramidException(String message) {
        super(message);
    }

    public InvalidPyramidException(String message, Throwable cause) {
        super(message, cause);
    }
}