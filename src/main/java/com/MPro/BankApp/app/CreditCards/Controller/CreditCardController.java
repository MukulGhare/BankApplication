package com.MPro.BankApp.app.CreditCards.Controller;

import com.MPro.BankApp.app.CreditCards.Service.CreditCardService;
import com.MPro.BankApp.app.CreditCards.dto.CreateCardDto;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import com.MPro.BankApp.app.CreditCards.dto.UseCardDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/crdcc")
public class CreditCardController {

    @Autowired
    private CreditCardService cardService;

    @Operation(
            tags ="CreditCard Controller",
            summary = "Get Card info",
            description = "Gets info of card via provided id"
    )
    @GetMapping("/card/{id}")
    public ResponseEntity<ReturnCardDetailsDto> getCardById(@PathVariable int id){
        ReturnCardDetailsDto cardDetails=cardService.getCardById(id);
        if (cardDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cardDetails);
    }

    @Operation(
            tags ="CreditCard Controller",
            summary = "Get All Customer Cards",
            description = "Gets info of all the cards the logged in customer owns"
    )
    @GetMapping("/cards")
    public ResponseEntity<List<ReturnCardDetailsDto>> getAllCustomerCards(){
        List<ReturnCardDetailsDto> cardDetails = cardService.getAllCustomerCards();
        if (cardDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cardDetails);
    }

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


    @PatchMapping("/pay-card")
    public ResponseEntity<ReturnCardDetailsDto> payCard(@RequestBody UseCardDto useCardDto){
        ReturnCardDetailsDto dto = cardService.payCard(useCardDto);

        return  ResponseEntity.ok(dto);
    }


    @PatchMapping("/cancel-card")
    public ResponseEntity<String> cancelCard(@RequestBody UseCardDto useCardDto){
        String response = cardService.cancelCard(useCardDto);
        return ResponseEntity.ok(response);
    }







}
