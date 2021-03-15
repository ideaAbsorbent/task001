package com.idea.absorbent.task001.product.persistence.repositories;

import com.idea.absorbent.task001.product.persistence.models.Product;
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
