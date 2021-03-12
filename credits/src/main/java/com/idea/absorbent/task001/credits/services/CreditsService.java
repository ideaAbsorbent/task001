package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateCustomerRequestBody;
import com.idea.absorbent.task001.credits.services.remote.dto.CreateProductRequestBody;
import com.idea.absorbent.task001.credits.persistence.models.Credit;
import com.idea.absorbent.task001.credits.persistence.repositories.CreditsRepository;

import com.idea.absorbent.task001.credits.web.dto.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CreditsService {

    CreditsRepository creditsRepository;
    ProductsService productsService;
    CustomersService customersService;

    public CreditsService(CreditsRepository creditsRepository, ProductsService productsService, CustomersService customersService) {
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
        //TODO error handling
        ProductDto p = productsService.creatProduct(productRequestBody);
        CustomerDto c = customersService.creatCustomer(customerReqBody);

        Credit credit = new Credit(CreditDto.getName());
        credit.setId(nextId);
        return creditsRepository.save(credit);
    }

    public Set<CreditFullRespDto> getCreditsWithCustomersAndProducts() {

        List<Credit> creds = creditsRepository.findAll();
        Set<Integer> creditIds = creds.stream().map(Credit::getId).collect(Collectors.toSet());


        Stream<CustomerDto> customers = customersService.getCustomersByCreditIds(creditIds).stream();
        Stream<ProductDto> products = productsService.getProductsByCustomerId(creditIds).stream();

        HashMap<Integer, CreditFullRespDto> responseData = new HashMap<>();

        creds.forEach(credit -> responseData.put(credit.getId(), new CreditFullRespDto(credit.getName())));
        customers.forEach(customer -> responseData.get(customer.getCreditId()).setCustomerResponse(customer));
        products.forEach(product -> responseData.get(product.getCreditId()).setProductResponse(product));

        return new HashSet<>(responseData.values());
    }
}