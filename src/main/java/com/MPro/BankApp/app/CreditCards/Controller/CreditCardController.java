package com.MPro.BankApp.app.CreditCards.Controller;

import com.MPro.BankApp.app.CreditCards.Service.CreditCardService;
import com.MPro.BankApp.app.CreditCards.dto.CreateCardDto;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import com.MPro.BankApp.app.CreditCards.dto.UseCardDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/crdcc")
public class CreditCardController {

    @Autowired
    private CreditCardService cardService;

    @Operation(
            tags="CreditCard Controller",
            summary = "Add new Card",
            description = "Adds a new card for logged in customer with provided maxLimit"
    )
    @PostMapping("/add-card")
    public ReturnCardDetailsDto addCard(@RequestBody CreateCardDto maxLimit){
        return cardService.addCard(maxLimit);
    }

    @Operation(
            tags="CrediCard Controller",
            summary = "Use card to Pay",
            description = "Card is being used to pay for something"
    )
    @PatchMapping("/use-card")
    public ReturnCardDetailsDto useCard(@RequestBody UseCardDto useCardDto){
        return cardService.useCard(useCardDto);
    }


}
