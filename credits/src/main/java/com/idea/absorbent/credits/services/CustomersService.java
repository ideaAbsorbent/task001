package com.idea.absorbent.credits.services;

import com.idea.absorbent.credits.dto.CreateCustomerRequestBody;
import com.idea.absorbent.credits.dto.CustomerDto;

import java.util.Set;

public interface CustomersService {
    Set<CustomerDto> getCustomersByCreditIds(Set<Integer> ids);
    CustomerDto createCustomer(CreateCustomerRequestBody dto);
}
