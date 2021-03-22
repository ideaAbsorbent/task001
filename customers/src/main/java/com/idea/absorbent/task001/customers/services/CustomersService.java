package com.idea.absorbent.task001.customers.services;

import com.idea.absorbent.task001.customers.persistence.models.Customer;
import com.idea.absorbent.task001.customers.web.dto.CreateCustomerDto;

import java.util.Set;

public interface CustomersService {
    Customer createCustomer(CreateCustomerDto customerDto);
    Set<Customer> getCustomersByCreditIds(Set<Integer> creditIds);
}
