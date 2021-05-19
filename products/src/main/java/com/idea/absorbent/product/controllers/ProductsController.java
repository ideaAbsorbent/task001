package com.idea.absorbent.product.controllers;

import com.idea.absorbent.product.dto.CreateProductDto;
import com.idea.absorbent.product.dto.ProductDto;
import com.idea.absorbent.product.exceptions.RequestParamFormatException;
import com.idea.absorbent.product.services.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
public class ProductsController {

    private ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createCustomer(@Valid @RequestBody CreateProductDto createProductDto) {
       return new ProductDto(productsService.createProduct(createProductDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<ProductDto> getCustomersByCreditIds(@RequestParam List<String> creditsIds) {
        Set<Integer> ids = creditsIds.stream().map(this::castParamToInt).collect(Collectors.toSet());

        return productsService.getCustomersByCreditIds(ids).stream().map(ProductDto::new).collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        productsService.removeProduct(id);
    }

    private Integer castParamToInt(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new RequestParamFormatException(value, "Integer");
        }
    }
}
