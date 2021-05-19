package com.idea.absorbent.customers.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String resource) {
        super(String.format("%s already exists", resource));
    }

}
