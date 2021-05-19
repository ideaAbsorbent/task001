package com.idea.absorbent.customers.exceptions;

public class RequestParamFormatException extends RuntimeException {
    public RequestParamFormatException(String param, String convertingTo) {
        super("Error converting request parameter {" + param + "} to type: " + convertingTo);
    }
}