package com.MPro.BankApp.app.CreditCards.Controller;

import com.MPro.BankApp.app.CreditCards.Service.CreditCardService;
import com.MPro.BankApp.app.CreditCards.dto.CreateCardDto;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
