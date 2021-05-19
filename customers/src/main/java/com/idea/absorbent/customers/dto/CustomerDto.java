package com.idea.absorbent.customers.dto;

import com.idea.absorbent.customers.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerDto {
    private Integer id;

    private Integer creditId;

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
