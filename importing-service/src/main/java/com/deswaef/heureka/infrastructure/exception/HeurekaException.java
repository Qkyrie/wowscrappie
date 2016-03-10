package com.deswaef.heureka.infrastructure.exception;

public class HeurekaException extends RuntimeException {
    public HeurekaException(String message) {
        super(message);
    }

    public HeurekaException(String message, Throwable cause) {
        super(message, cause);
    }
}
