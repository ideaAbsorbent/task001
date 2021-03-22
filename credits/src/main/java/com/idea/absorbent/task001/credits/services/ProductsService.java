package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateProductRequestBody;
import com.idea.absorbent.task001.credits.web.dto.ProductDto;

import java.util.Set;

public interface ProductsService {
    Set<ProductDto> getProductsByCustomerId(Set<Integer> ids);
    ProductDto createProduct(CreateProductRequestBody dto);
    void removeProduct(int productId);
}
