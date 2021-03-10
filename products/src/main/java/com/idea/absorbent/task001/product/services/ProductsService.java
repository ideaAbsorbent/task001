package com.idea.absorbent.task001.product.services;


import com.idea.absorbent.task001.product.persistence.models.Product;
import com.idea.absorbent.task001.product.persistence.repositories.ProductsRepository;
import com.idea.absorbent.task001.product.web.error.ResourceAlreadyExistsException;
import com.idea.absorbent.task001.product.web.dto.CreateProductDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductsService {

    ProductsRepository customersRepo;

    public ProductsService(ProductsRepository customersRepo) {
        this.customersRepo = customersRepo;
    }

    public Product createProduct(CreateProductDto productDto) {
        Product customer = new Product(productDto);
        if (customerExists(customer))
            throw new ResourceAlreadyExistsException(Product.class.getSimpleName());

        return customersRepo.save(customer);
    }

    public Set<Product> getCustomersByCreditIds(Set<Integer> creditIds) {
        return customersRepo.getProductByCreditIdIn(creditIds);
    }

    private boolean customerExists(Product p) {
        return customersRepo.existsByCreditId(p.getCreditId());
    }
}