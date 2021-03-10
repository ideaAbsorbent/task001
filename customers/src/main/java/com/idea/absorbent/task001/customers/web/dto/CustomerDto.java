package com.idea.absorbent.task001.customers.web.dto;

import com.idea.absorbent.task001.customers.persistence.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerDto {
    private Long id;

    private Long creditId;

    private String firstName;

    private String surname;

    private String pesel;

    public CustomerDto(Customer c) {
        this(
            c.getId(),
            c.getCreditId(),
            c.getFirstName(),
            c.getSurname(),
            c.getPesel()
        );
    }
}
