package com.idea.absorbent.customers.services;

import com.idea.absorbent.customers.dto.CreateCustomerDto;
import com.idea.absorbent.customers.exceptions.ResourceAlreadyExistsException;
import com.idea.absorbent.customers.models.Customer;
import com.idea.absorbent.customers.repositories.CustomersRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomersServiceImpl implements CustomersService {

    CustomersRepository customersRepo;

    public CustomersServiceImpl(CustomersRepository customersRepo) {
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