package com.idea.absorbent.task001.credits.web.dto;

import com.idea.absorbent.task001.credits.persistence.models.Credit;
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

