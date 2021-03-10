package com.idea.absorbent.task001.product.web.error;

public class RequestParamFormatException extends RuntimeException {
    public RequestParamFormatException(String param, String convertingTo) {
        super("Error converting request parameter {" + param + "} to type: " + convertingTo);
    }
}