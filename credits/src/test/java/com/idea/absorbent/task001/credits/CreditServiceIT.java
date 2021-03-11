package com.idea.absorbent.task001.credits;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateCustomerRequestBody;
import com.idea.absorbent.task001.credits.services.remote.dto.CreateProductRequestBody;
import com.idea.absorbent.task001.credits.persistence.models.Credit;
import com.idea.absorbent.task001.credits.persistence.repositories.CreditsRepository;
import com.idea.absorbent.task001.credits.services.CreditsService;
import com.idea.absorbent.task001.credits.services.CustomersService;
import com.idea.absorbent.task001.credits.services.ProductsService;
import com.idea.absorbent.task001.credits.web.dto.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.DynamicTest;

import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class CreditServiceIT {

    private CreditsService testedService;
    private ProductsService productsService;
    private CustomersService customersService;

    @Autowired
    public CreditServiceIT(CreditsRepository creditsRepository) {
        this.productsService = Mockito.mock(ProductsService.class);
        this.customersService = Mockito.mock(CustomersService.class);
        this.testedService = new CreditsService(creditsRepository, productsService, customersService);
    }

    @Transactional
    @TestFactory
    Iterable<DynamicTest> shouldCreatCredit() {
        //given request
        CreateProductDto productDto = new CreateProductDto();
        productDto.setValue(1200);
        CreateCustomerDto customerDto = new CreateCustomerDto();
        customerDto.setFirstname("testName");
        customerDto.setSurname("testSurname");
        customerDto.setPesel("85022463945");

        CreateCreditDto creditDto = new CreateCreditDto("testCredit", customerDto, productDto);

        Mockito.when(productsService.creatProduct(Mockito.any(CreateProductRequestBody.class)))
                .thenReturn(new ProductDto(productDto.getValue()));

        Mockito.when(customersService.creatCustomer((Mockito.any(CreateCustomerRequestBody.class))))
                .thenReturn(new CustomerDto(customerDto.getFirstname(), customerDto.getSurname(), customerDto.getPesel()));

        //then
        Credit credit = testedService.createCredit(creditDto);

        //should
        List<DynamicTest> tests = new ArrayList<>();
        tests.add(dynamicTest("Credit id should be set",
                () -> assertNotNull(credit.getId())));
        tests.add(dynamicTest("Credit name should equal dto name",
                () -> assertEquals(creditDto.getName(), credit.getName())));
        tests.add(dynamicTest("Credit id should be set in product dto",
                () -> assertEquals(credit.getId(), productDto.getCreditId())));
        tests.add(dynamicTest("Credit id should be set in customer dto",
                () -> assertEquals(credit.getId(), customerDto.getCreditId())));

        return tests;
    }

}