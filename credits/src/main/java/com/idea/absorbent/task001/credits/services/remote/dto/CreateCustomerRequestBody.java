package com.idea.absorbent.task001.credits.services.remote.dto;

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

