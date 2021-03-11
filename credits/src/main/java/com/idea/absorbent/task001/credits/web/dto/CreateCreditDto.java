package com.idea.absorbent.task001.credits.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateCreditDto {

        @NotBlank(message = "name must not be blank")
        private String name;

        @NotNull(message = "customer object required")
        @Valid
        private CreateCustomerDto customer;

        @NotNull(message = "product object required")
        @Valid
        private CreateProductDto product;

}
