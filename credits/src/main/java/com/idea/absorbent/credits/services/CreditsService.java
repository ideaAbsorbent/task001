package com.idea.absorbent.credits.services;

import com.idea.absorbent.credits.models.Credit;
import com.idea.absorbent.credits.dto.CreateCreditDto;
import com.idea.absorbent.credits.dto.CreditFullRespDto;

import java.util.Set;

public interface CreditsService {
    Credit createCredit(CreateCreditDto CreditDto);
    Set<CreditFullRespDto> getCreditsWithCustomersAndProducts();
}
