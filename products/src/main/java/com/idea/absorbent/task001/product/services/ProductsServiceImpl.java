package com.idea.absorbent.task001.product.services;


import com.idea.absorbent.task001.product.persistence.models.Product;
import com.idea.absorbent.task001.product.persistence.repositories.ProductsRepository;
import com.idea.absorbent.task001.product.web.error.ResourceAlreadyExistsException;
import com.idea.absorbent.task001.product.web.dto.CreateProductDto;
import com.idea.absorbent.task001.product.web.error.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductsServiceImpl implements ProductsService {

    ProductsRepository customersRepo;

    public ProductsServiceImpl(ProductsRepository customersRepo) {
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

    public void removeProduct(int productId) {
        if(!productExists(productId))
            throw new ResourceNotFoundException("Product");
        customersRepo.deleteById(productId);
    }

    public boolean productExists(int productId) {
        return customersRepo.existsById(productId);
    }

    private boolean customerExists(Product p) {
        return customersRepo.existsByCreditId(p.getCreditId());
    }
}