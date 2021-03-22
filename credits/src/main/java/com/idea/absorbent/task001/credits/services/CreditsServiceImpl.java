package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateCustomerRequestBody;
import com.idea.absorbent.task001.credits.services.remote.dto.CreateProductRequestBody;
import com.idea.absorbent.task001.credits.persistence.models.Credit;
import com.idea.absorbent.task001.credits.persistence.repositories.CreditsRepository;

import com.idea.absorbent.task001.credits.web.dto.*;
import com.idea.absorbent.task001.credits.web.error.ServiceUnavailableException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CreditsServiceImpl implements CreditsService{

    CreditsRepository creditsRepository;
    ProductsService productsService;
    CustomersService customersService;

    public CreditsServiceImpl(CreditsRepository creditsRepository, ProductsService productsService, CustomersService customersService) {
        this.creditsRepository = creditsRepository;
        this.productsService = productsService;
        this.customersService = customersService;
    }

    public Credit createCredit(CreateCreditDto CreditDto) {
        int nextId = creditsRepository.getNextValMySequence();

        CreateCustomerDto customerDto = CreditDto.getCustomer();
        customerDto.setCreditId(nextId);
        CreateCustomerRequestBody customerReqBody = new CreateCustomerRequestBody(
                customerDto.getCreditId(),
                customerDto.getFirstname(),
                customerDto.getSurname(),
                customerDto.getPesel());

        CreateProductDto productDto = CreditDto.getProduct();
        productDto.setCreditId(nextId);

        CreateProductRequestBody productRequestBody = new CreateProductRequestBody(
                productDto.getCreditId(),
                productDto.getValue()
        );
        ProductDto responseProductDto;
        try {
            responseProductDto = productsService.createProduct(productRequestBody);
        } catch (Exception e) {
            throw new ServiceUnavailableException();
        }

        try {
            customersService.createCustomer(customerReqBody);
        } catch (Exception e) {
            productsService.removeProduct(responseProductDto.getId());
            throw new ServiceUnavailableException();
        }

        Credit credit = new Credit(CreditDto.getName());
        credit.setId(nextId);
        return creditsRepository.save(credit);
    }

    public Set<CreditFullRespDto> getCreditsWithCustomersAndProducts() {

        List<Credit> credits = creditsRepository.findAll();
        Set<Integer> creditIds = credits.stream().map(Credit::getId).collect(Collectors.toSet());

        Stream<CustomerDto> customers;
        Stream<ProductDto> products;
        try {
            customers = customersService.getCustomersByCreditIds(creditIds).stream();
            products = productsService.getProductsByCustomerId(creditIds).stream();
        } catch (Exception e) {
            throw new ServiceUnavailableException();
        }

        HashMap<Integer, CreditFullRespDto> responseData = new HashMap<>();

        credits.forEach(credit -> responseData.put(credit.getId(), new CreditFullRespDto(credit.getName())));
        customers.forEach(customer -> responseData.get(customer.getCreditId()).setCustomerResponse(customer));
        products.forEach(product -> responseData.get(product.getCreditId()).setProductResponse(product));

        return new HashSet<>(responseData.values());
    }
}