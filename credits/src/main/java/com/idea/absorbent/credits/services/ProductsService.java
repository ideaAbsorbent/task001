package com.idea.absorbent.credits.services;

import com.idea.absorbent.credits.dto.CreateProductRequestBody;
import com.idea.absorbent.credits.dto.ProductDto;

import java.util.Set;

public interface ProductsService {
    Set<ProductDto> getProductsByCustomerId(Set<Integer> ids);
    ProductDto createProduct(CreateProductRequestBody dto);
    void removeProduct(int productId);
}
