package com.idea.absorbent.task001.credits.services;

import com.idea.absorbent.task001.credits.persistence.models.Credit;
import com.idea.absorbent.task001.credits.web.dto.CreateCreditDto;
import com.idea.absorbent.task001.credits.web.dto.CreditFullRespDto;

import java.util.Set;

public interface CreditsService {
    Credit createCredit(CreateCreditDto CreditDto);
    Set<CreditFullRespDto> getCreditsWithCustomersAndProducts();
}
