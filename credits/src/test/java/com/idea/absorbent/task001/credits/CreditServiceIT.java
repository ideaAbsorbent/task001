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
import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
public class CreditServiceIT {

    private CreditsService testedService;
    private ProductsService productsServiceMock;
    private CustomersService customersServiceMock;

    @Autowired
    public CreditServiceIT(CreditsRepository creditsRepository) {
        this.productsServiceMock = Mockito.mock(ProductsService.class);
        this.customersServiceMock = Mockito.mock(CustomersService.class);
        this.testedService = new CreditsService(creditsRepository, productsServiceMock, customersServiceMock);
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

        Mockito.when(productsServiceMock.creatProduct(Mockito.any(CreateProductRequestBody.class)))
                .thenReturn(new ProductDto(productDto.getValue()));

        Mockito.when(customersServiceMock.creatCustomer((Mockito.any(CreateCustomerRequestBody.class))))
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

    @Transactional
    @TestFactory
    Iterable<DynamicTest> shouldGetCreditsList() {

        //Given some credits
        creatDataForShouldGetCreditsList();

        //when getting full response data
        Set<CreditFullRespDto> credits = testedService.getCreditsWithCustomersAndProducts();

        //should
        List<DynamicTest> tests = new ArrayList<>();

        credits.forEach(element -> {
            tests.add(dynamicTest("has name", () -> assertNotNull(element.getName())));
            tests.add(dynamicTest("has Customer data", () -> assertNotNull(element.getCustomerResponse())));
            tests.add(dynamicTest("has Product data", () -> assertNotNull(element.getProductResponse())));
        });

        return tests;
    }


    private void creatDataForShouldGetCreditsList() {
        //TODO some more reasonable name
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setValue(1200);
        CreateCustomerDto createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setFirstname("testName");
        createCustomerDto.setSurname("testSurname");
        createCustomerDto.setPesel("85022463945");

        CreateCreditDto creditDto = new CreateCreditDto("testCredit", createCustomerDto, createProductDto);

        Mockito.when(productsServiceMock.creatProduct(Mockito.any(CreateProductRequestBody.class)))
                .thenReturn(new ProductDto(createProductDto.getValue()));

        Mockito.when(customersServiceMock.creatCustomer((Mockito.any(CreateCustomerRequestBody.class))))
                .thenReturn(new CustomerDto(createCustomerDto.getFirstname(), createCustomerDto.getSurname(), createCustomerDto.getPesel()));

        Credit creditA = testedService.createCredit(creditDto);

        CustomerDto customerDto = new CustomerDto(
                creditA.getId(),
            createCustomerDto.getFirstname(),
            createCustomerDto.getSurname(),
            createCustomerDto.getPesel()
        );

        ProductDto productDto = new ProductDto(
                creditA.getId(),
                createProductDto.getValue()
        );

        Mockito.when(customersServiceMock.getCustomersByCreditIds(Set.of(creditA.getId())))
                .thenReturn(Set.of(customerDto));

        Mockito.when(productsServiceMock.getProductsByCustomerId(Set.of(creditA.getId())))
                .thenReturn(Set.of(productDto));

    }
}