package com.idea.absorbent.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.pl.PESEL;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class CreateCustomerDto {

    @Min(value = 0, message = "creditId must be greater or equal than 0")
    private Integer creditId;

    @NotBlank(message = "firstName may not be empty")
    private String firstName;

    @NotBlank(message = "surname may not be empty")
    private String surname;

    @PESEL
    private String pesel;
}
