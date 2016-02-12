package com.deswaef.wowscrappie.infrastructure.exception;

public class WowscrappieException extends RuntimeException {

    public WowscrappieException(String message) {
        super(message);
    }

    public WowscrappieException(String message, Throwable cause) {
        super(message, cause);
    }
}
