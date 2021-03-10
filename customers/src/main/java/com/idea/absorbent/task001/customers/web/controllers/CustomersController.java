package com.idea.absorbent.task001.customers.web.controllers;

import com.idea.absorbent.task001.customers.persistence.models.Customer;
import com.idea.absorbent.task001.customers.services.CustomersService;
import com.idea.absorbent.task001.customers.web.dto.CreateCustomerDto;
import com.idea.absorbent.task001.customers.web.dto.CustomerDto;
import com.idea.absorbent.task001.customers.web.error.RequestParamFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customers")
public class CustomersController {
    //TODO add error handling

    private CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto creatCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
       return new CustomerDto(customersService.createCustomer(createCustomerDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<CustomerDto> getCustomersByCreditIds(@RequestParam List<String> creditsIds) {
        Set<Long> ids = creditsIds.stream().map(this::castParamToLong).collect(Collectors.toSet());

        return customersService.getCustomersByCreditIds(ids).stream().map(CustomerDto::new).collect(Collectors.toSet());
    }

    private Long castParamToLong(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new RequestParamFormatException(value, "Long");
        }
    }
}
