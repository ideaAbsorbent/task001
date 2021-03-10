package com.idea.absorbent.task001.product.web.error;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String resource) {
        super(String.format("%s already exists", resource));
    }

}
