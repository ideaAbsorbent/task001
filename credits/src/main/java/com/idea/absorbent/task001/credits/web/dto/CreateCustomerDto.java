package com.idea.absorbent.task001.credits.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCustomerDto {

    private Integer creditId;

    @NotBlank(message = "firstName may not be empty")
    private String firstname;

    @NotBlank(message = "surname may not be empty")
    private String surname;

    @NotBlank(message = "pesel may not be empty")
    @PESEL
    private String pesel;
}
