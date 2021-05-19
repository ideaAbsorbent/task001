package com.idea.absorbent.product.exceptions;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String resource) {
        super(resource + " not found");
    }
}
