package com.idea.absorbent.credits.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Integer creditId;
    private String firstName;
    private String surname;
    private String pesel;

    public CustomerDto(String firstName, String surname, String pesel) {
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
    }
}
