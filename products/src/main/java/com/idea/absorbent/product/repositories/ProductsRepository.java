package com.idea.absorbent.product.repositories;

import com.idea.absorbent.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {

    Set<Product> getProductByCreditIdIn(Iterable<Integer> iterable);

    Boolean existsByCreditId(Integer creditId);

    @Override
    void deleteById(Integer integer);
}
