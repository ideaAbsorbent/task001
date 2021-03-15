package com.idea.absorbent.task001.credits.web.error;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException() {
        super("Could not reach service. Please try again later.");
    }
}
