package com.idea.absorbent.credits.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateProductDto {

    private Integer creditId;

    @NotNull(message = "field: value, must be set")
    @Min(value = 0, message = "field: value, required greater than 0")
    private Integer value;

}
