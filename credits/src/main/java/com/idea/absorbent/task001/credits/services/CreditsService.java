package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateCustomerRequestBody;
import com.idea.absorbent.task001.credits.services.remote.dto.CreateProductRequestBody;
import com.idea.absorbent.task001.credits.persistence.models.Credit;
import com.idea.absorbent.task001.credits.persistence.repositories.CreditsRepository;

import com.idea.absorbent.task001.credits.web.dto.*;
import org.springframework.stereotype.Service;

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


    //TODO get Credits
//    public Set<Credit> getCredits(Set<Integer> creditIds) {
//        /*
//        * get all credits with ids
//        *
//        * get all customers with ids
//        *
//        * get all products with ids
//        *
//        * map credits to dto + map customers + map products
//        */
//    }

}