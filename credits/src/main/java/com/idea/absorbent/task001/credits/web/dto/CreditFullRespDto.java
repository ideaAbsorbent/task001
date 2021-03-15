package com.idea.absorbent.task001.credits.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreditFullRespDto {

    private String name;

    private CustomerResponse customerResponse;

    private ProductResponse productResponse;

    public CreditFullRespDto(String name) {
        this.name = name;
    }

    @AllArgsConstructor
    @Getter
    private static class CustomerResponse {
        private String firstName;
        private String surname;
        private String pesel;
    }

    @AllArgsConstructor
    @Getter
    private static class ProductResponse {
        private int value;
    }

    public void setCustomerResponse(CustomerDto customerDto) {
        this.customerResponse = new CustomerResponse(
                customerDto.getFirstName(),
                customerDto.getSurname(),
                customerDto.getPesel());
    }

    public void setProductResponse(ProductDto productDto) {
        this.productResponse = new ProductResponse(
                productDto.getValue()
        );
    }
}