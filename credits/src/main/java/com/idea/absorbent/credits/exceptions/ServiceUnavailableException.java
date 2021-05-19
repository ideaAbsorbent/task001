package com.idea.absorbent.credits.exceptions;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException() {
        super("Could not reach service. Please try again later.");
    }
}
