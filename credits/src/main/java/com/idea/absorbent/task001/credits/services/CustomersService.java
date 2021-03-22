package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.services.remote.dto.CreateCustomerRequestBody;
import com.idea.absorbent.task001.credits.web.dto.CustomerDto;

import java.util.Set;

public interface CustomersService {
    Set<CustomerDto> getCustomersByCreditIds(Set<Integer> ids);
    CustomerDto createCustomer(CreateCustomerRequestBody dto);
}
