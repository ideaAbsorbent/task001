package com.idea.absorbent.customers;

import com.idea.absorbent.customers.models.Customer;
import com.idea.absorbent.customers.services.CustomersServiceImpl;
import com.idea.absorbent.customers.dto.CreateCustomerDto;

import com.idea.absorbent.customers.exceptions.ResourceAlreadyExistsException;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@SpringBootTest
@ActiveProfiles("test")
public class CustomersServiceTest {

    CustomersServiceImpl testedService;

    @Autowired
    public CustomersServiceTest(CustomersServiceImpl testedService) {
        this.testedService = testedService;
    }

    @Test
    @Transactional
     void shouldSaveCustomer() {
        //given a valid customer DTO
        CreateCustomerDto dto = new CreateCustomerDto(1,"TestName","TestSurname","84010329229");

        //when creating the customer
        Customer customer =  this.testedService.createCustomer(dto);

        //then return a correct customer
        assertAll(
            () -> assertEquals(customer.getFirstName(), dto.getFirstName(),"first name is correct"),
            () -> assertEquals(customer.getSurname(), dto.getSurname(), "surname is correct"),
            () -> assertEquals(customer.getPesel(), dto.getPesel(), "pesel is correct"),
            () -> assertEquals(customer.getCreditId(), dto.getCreditId(),"creditId is correct"),
            () -> assertNotNull(customer.getId(), "id is set")
        );
    }

    @Test
    @Transactional
    void shouldThrowResourceAlreadyExists() {
        //given an existing customer
        CreateCustomerDto dto = new CreateCustomerDto(12,"TestName","TestSurname","84010329229");
        Customer customer =  this.testedService.createCustomer(dto);

        CreateCustomerDto duplicate = new CreateCustomerDto(12,"differentName","differentSurname","84010329229");
        //when creating a duplicate

        //then throw and exception
        assertThrows(ResourceAlreadyExistsException.class, () -> this.testedService.createCustomer(duplicate));
    }

    @TestFactory
     Iterable<DynamicTest> shouldRetrieveCustomers() {
        List<CreateCustomerDto> data = new ArrayList<>();

        data.add(new CreateCustomerDto(2,"TestName2","TestSurname2","50030133298"));
        data.add(new CreateCustomerDto(3,"TestName3","TestSurname3","91032885165"));
        data.add(new CreateCustomerDto(3,"TestName4","TestSurname4","87030586573"));
        data.add(new CreateCustomerDto(4,"TestName5","TestSurname5","49050843791"));
        data.add(new CreateCustomerDto(7,"TestName6","TestSurname6","93101452542"));

        for (CreateCustomerDto dto: data) {
            this.testedService.createCustomer(dto);
        }

        final Set<Integer> creditIds = Set.of(1, 2, 3, 4);
        final List<Customer> toMatch = data.stream().map(Customer::new).collect(Collectors.toList());

        Set<Customer> customers = this.testedService.getCustomersByCreditIds(creditIds);
        List<DynamicTest> tests = new ArrayList<>();

        customers.forEach(
            customer -> {
               tests.add(dynamicTest("customerId should exists in request param", () -> assertTrue(creditIds.contains(customer.getCreditId()))));
               tests.add(dynamicTest("customer was created in this test", () -> assertEquals(Optional.of(customer), matchingCustomer(customer, toMatch))));
            }
        );
        return tests;
    }

    private Optional<Customer> matchingCustomer(Customer customer, List<Customer> customers) {
        return customers.stream().filter(customer::equals).findFirst();
    }
}