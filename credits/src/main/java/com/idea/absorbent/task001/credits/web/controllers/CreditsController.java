package com.idea.absorbent.task001.credits.web.controllers;

import com.idea.absorbent.task001.credits.services.CreditsService;
import com.idea.absorbent.task001.credits.web.dto.CreateCreditDto;
import com.idea.absorbent.task001.credits.web.dto.CreditDto;
import com.idea.absorbent.task001.credits.web.dto.CreditFullRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/credits")
public class CreditsController {

    CreditsService creditsService;

    public CreditsController(CreditsService creditsService) {
        this.creditsService = creditsService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreditDto createCredit(@Valid @RequestBody CreateCreditDto createCreditDto) {
        return new CreditDto(creditsService.createCredit(createCreditDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<CreditFullRespDto> getCredits() {

        return creditsService.getCreditsWithCustomersAndProducts();
    }
}
