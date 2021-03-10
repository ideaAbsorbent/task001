package com.idea.absorbent.task001.customers.persistence.repositories;

import com.idea.absorbent.task001.customers.persistence.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {

    Set<Customer> getCustomersByCreditIdIn(Iterable<Long> iterable);

    Boolean existsByCreditIdAndPesel(Long creditId, String pesel);
}
