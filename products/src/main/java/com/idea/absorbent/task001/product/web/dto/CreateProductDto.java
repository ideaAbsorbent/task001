package com.idea.absorbent.task001.product.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductDto {

    @Min(value = 0, message = "creditId must be greater or equal than 0")
    private Integer creditId;

    @NotNull(message = "value must be set")
    private Integer value;

}
