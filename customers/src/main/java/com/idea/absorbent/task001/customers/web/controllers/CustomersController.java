package com.idea.absorbent.task001.customers.web.controllers;

import com.idea.absorbent.task001.customers.persistence.models.Customer;
import com.idea.absorbent.task001.customers.services.CustomersService;
import com.idea.absorbent.task001.customers.web.dto.CreateCustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/customers")
public class CustomersController {
    //TODO add error handling

    private CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer creatCustomer(@Valid CreateCustomerDto createCustomerDto) {
       return customersService.createCustomer(createCustomerDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Customer> getCustomersByCreditIds(@RequestParam Set<Long> creditsIds) {
        return customersService.getCustomersByCreditIds(creditsIds);
    }

}
