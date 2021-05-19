package com.idea.absorbent.credits.dto;

import com.idea.absorbent.credits.models.Credit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private Integer id;

    public CreditDto(Credit c) {
        this(c.getId());
    }

}

