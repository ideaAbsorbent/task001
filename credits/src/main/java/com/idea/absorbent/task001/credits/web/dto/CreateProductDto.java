package com.idea.absorbent.task001.credits.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class CreateProductDto {

    private Integer creditId;

    @Min(value = 0, message = "field: value, required greater than 0")
    private Integer value;

}
