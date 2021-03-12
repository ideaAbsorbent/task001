package com.idea.absorbent.task001.credits.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer creditId;
    private int value;

    public ProductDto(int value) {
        this.value = value;
    }
}
