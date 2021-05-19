package com.idea.absorbent.product.services;

import com.idea.absorbent.product.models.Product;
import com.idea.absorbent.product.dto.CreateProductDto;

import java.util.Set;

public interface ProductsService {
    Product createProduct(CreateProductDto productDto);
    Set<Product> getCustomersByCreditIds(Set<Integer> creditIds);
    void removeProduct(int productId);
}
