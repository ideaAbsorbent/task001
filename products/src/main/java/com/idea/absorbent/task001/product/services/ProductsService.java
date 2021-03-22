package com.idea.absorbent.task001.product.services;

import com.idea.absorbent.task001.product.persistence.models.Product;
import com.idea.absorbent.task001.product.web.dto.CreateProductDto;

import java.util.Set;

public interface ProductsService {
    Product createProduct(CreateProductDto productDto);
    Set<Product> getCustomersByCreditIds(Set<Integer> creditIds);
    void removeProduct(int productId);
}
