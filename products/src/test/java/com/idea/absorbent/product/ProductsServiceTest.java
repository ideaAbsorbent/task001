package com.idea.absorbent.product;

import com.idea.absorbent.product.exceptions.ResourceAlreadyExistsException;
import com.idea.absorbent.product.exceptions.ResourceNotFoundException;
import com.idea.absorbent.product.models.Product;
import com.idea.absorbent.product.services.ProductsServiceImpl;
import com.idea.absorbent.product.dto.CreateProductDto;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@SpringBootTest
@ActiveProfiles("test")
public class ProductsServiceTest {

    ProductsServiceImpl testedService;

    @Autowired
    public ProductsServiceTest(ProductsServiceImpl testedService) {
        this.testedService = testedService;
    }

    @Test
    @Transactional
     void shouldSaveProduct() {
        //given a valid customer DTO
        CreateProductDto dto = new CreateProductDto(1, 35000);

        //when creating the customer
        Product product =  this.testedService.createProduct(dto);

        //then return a correct customer
        assertAll(
            () -> assertEquals(product.getValue(), dto.getValue(), "value is correct"),
            () -> assertEquals(product.getCreditId(), dto.getCreditId(),"creditId is correct"),
            () -> assertNotNull(product.getId(), "id is set")
        );
    }

    @Test
    @Transactional
    void shouldThrowResourceAlreadyExists() {
        //given an existing customer
        CreateProductDto dto = new CreateProductDto(12,455000);
        Product product =  this.testedService.createProduct(dto);

        //when creating a duplicate
        CreateProductDto duplicate = new CreateProductDto(12,570000);

        //then throw and exception
        assertThrows(ResourceAlreadyExistsException.class, () -> this.testedService.createProduct(duplicate));
    }

    @TestFactory
    @Transactional
     Iterable<DynamicTest> shouldRetrieveProducts() {
        List<CreateProductDto> data = new ArrayList<>();

        data.add(new CreateProductDto(2,478352042));
        data.add(new CreateProductDto(3,17564042));
        data.add(new CreateProductDto(6,53412042));
        data.add(new CreateProductDto(4,23352042));
        data.add(new CreateProductDto(7,4352042));

        for (CreateProductDto dto: data) {
            this.testedService.createProduct(dto);
        }

        final Set<Integer> creditIds = Set.of(1, 2, 3, 4);
        final List<Product> toMatch = data.stream().map(Product::new).collect(Collectors.toList());

        Set<Product> products = this.testedService.getCustomersByCreditIds(creditIds);
        List<DynamicTest> tests = new ArrayList<>();

        products.forEach(
            customer -> {
               tests.add(dynamicTest("productId should exists in request param", () -> assertTrue(creditIds.contains(customer.getCreditId()))));
               tests.add(dynamicTest("product was created in this test", () -> assertEquals(Optional.of(customer), matchingProduct(customer, toMatch))));
            }
        );
        return tests;
    }

    @Test
    @Transactional
    void shouldRemoveProduct() {
        //given a saved product
        CreateProductDto dto = new CreateProductDto(76, 98400);
        Product product =  this.testedService.createProduct(dto);

        //when removing product
        testedService.removeProduct(product.getId());

        //should remove form db
        assertFalse(testedService.productExists(product.getId()));
    }

    @Test
    @Transactional
    void shouldThrowResourceNotFoundException() {
        //given a non existing product id
        final int id = 105;

        //when removing product
        //should throw ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> testedService.removeProduct(id));
    }

    private Optional<Product> matchingProduct(Product product, List<Product> products) {
        return products.stream().filter(product::equals).findFirst();
    }
}