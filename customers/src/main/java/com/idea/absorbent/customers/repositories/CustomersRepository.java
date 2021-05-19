package com.idea.absorbent.customers.repositories;

import com.idea.absorbent.customers.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

    Set<Customer> getCustomersByCreditIdIn(Iterable<Integer> iterable);

    Boolean existsByCreditIdAndPesel(Integer creditId, String pesel);
}
