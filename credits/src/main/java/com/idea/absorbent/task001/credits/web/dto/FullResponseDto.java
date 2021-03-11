package com.idea.absorbent.task001.credits.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FullResponseDto {

    private String name;

    private CustomerDto customerDto;

    private ProductDto productDto;

    public FullResponseDto(String name) {
        this.name = name;
    }

}