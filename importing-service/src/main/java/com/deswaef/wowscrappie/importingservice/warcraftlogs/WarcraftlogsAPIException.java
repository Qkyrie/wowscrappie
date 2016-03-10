package com.deswaef.wowscrappie.importingservice.warcraftlogs;

public class WarcraftlogsAPIException extends Exception {

    public WarcraftlogsAPIException(String message) {
        super(message);
    }

    public WarcraftlogsAPIException() {
    }

    public WarcraftlogsAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
