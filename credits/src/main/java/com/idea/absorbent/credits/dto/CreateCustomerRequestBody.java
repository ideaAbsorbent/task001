package com.idea.absorbent.credits.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCustomerRequestBody {

    private Integer creditId;
    private String firstName;
    private String surname;
    private String pesel;

}

