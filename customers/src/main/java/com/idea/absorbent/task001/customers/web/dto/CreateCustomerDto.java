package com.idea.absorbent.task001.customers.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateCustomerDto {

    //TODO validation
    private Long creditId;

    private String firstName;

    private String surname;

    private String pesel;
}
