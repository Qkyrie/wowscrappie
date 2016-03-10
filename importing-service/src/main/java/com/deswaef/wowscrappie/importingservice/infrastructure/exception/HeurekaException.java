package com.deswaef.wowscrappie.importingservice.infrastructure.exception;

public class HeurekaException extends RuntimeException {
    public HeurekaException(String message) {
        super(message);
    }

    public HeurekaException(String message, Throwable cause) {
        super(message, cause);
    }
}
