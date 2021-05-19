package com.idea.absorbent.product.dto;

import com.idea.absorbent.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ProductDto {
    private Integer id;

    private Integer creditId;

    private Integer value;

    public ProductDto(Product p) {
        this(
            p.getId(),
            p.getCreditId(),
            p.getValue()
        );
    }

}
