package com.idea.absorbent.task001.credits.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int id;
    private Integer creditId;
    private int value;

    public ProductDto(int value) {
        this.value = value;
    }

    public ProductDto(Integer creditId, int value) {
        this.creditId = creditId;
        this.value = value;
    }
}
