package com.idea.absorbent.task001.customers.services;

import com.idea.absorbent.task001.customers.persistence.models.Customer;
import com.idea.absorbent.task001.customers.persistence.repositories.CustomersRepository;
import com.idea.absorbent.task001.customers.web.dto.CreateCustomerDto;
import com.idea.absorbent.task001.customers.web.error.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomersService {

    CustomersRepository customersRepo;

    public CustomersService(CustomersRepository customersRepo) {
        this.customersRepo = customersRepo;
    }

    public Customer createCustomer(CreateCustomerDto customerDto) {
        Customer customer = new Customer(customerDto);
        if (customerExists(customer))
            throw new ResourceAlreadyExistsException(Customer.class.getSimpleName());

        return customersRepo.save(customer);
    }

    public Set<Customer> getCustomersByCreditIds(Set<Integer> creditIds) {
        return customersRepo.getCustomersByCreditIdIn(creditIds);
    }

    private boolean customerExists(Customer c) {
        return customersRepo.existsByCreditIdAndPesel(c.getCreditId(), c.getPesel());
    }
}