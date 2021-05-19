package com.idea.absorbent.product.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String resource) {
        super(String.format("%s already exists", resource));
    }

}
