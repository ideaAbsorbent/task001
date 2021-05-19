package com.idea.absorbent.customers.services;

import com.idea.absorbent.customers.models.Customer;
import com.idea.absorbent.customers.dto.CreateCustomerDto;

import java.util.Set;

public interface CustomersService {
    Customer createCustomer(CreateCustomerDto customerDto);
    Set<Customer> getCustomersByCreditIds(Set<Integer> creditIds);
}
