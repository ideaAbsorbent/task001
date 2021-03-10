package com.idea.absorbent.task001.product.web.dto;

import com.idea.absorbent.task001.product.persistence.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ProductDto {
    private Integer id;

    private Integer creditId;

    private Integer Value;

    public ProductDto(Product p) {
        this(
            p.getId(),
            p.getCreditId(),
            p.getValue()
        );
    }

}
