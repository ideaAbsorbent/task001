package com.idea.absorbent.task001.product.web.error;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String resource) {
        super(resource + " not found");
    }
}
