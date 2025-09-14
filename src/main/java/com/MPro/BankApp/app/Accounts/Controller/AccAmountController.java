package com.MPro.BankApp.app.Accounts.Controller;

import com.MPro.BankApp.app.Accounts.Service.AmountService;
import com.MPro.BankApp.app.Accounts.dto.AccAmountTransferDto;
import com.MPro.BankApp.app.Accounts.dto.AccSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.math.BigDecimal;

@RestController
@RequestMapping("/acc/tran/")
public class AccAmountController {

    @Autowired
    private AmountService amountService;

    @Operation(
            tags= "Amount Controller",
            summary = "Transfer Amount",
            description = "Transfers amount from one account to another"
    )
    @PostMapping("/transfer")
    public ResponseEntity<String> transferAmount(@RequestBody AccAmountTransferDto accAmountTransferDto){
         BigDecimal updatedBalance = amountService.transferAmount(accAmountTransferDto);
         return new ResponseEntity<>("Current Balance : "+updatedBalance, HttpStatus.OK);
    }

    @Operation(
            tags="Amount Controller",
            summary="Deposit Amount",
            description="Deposits amount in provided bank"
    )
    @PatchMapping("/deposit")
    public ResponseEntity<String> depositAmount(@RequestBody AccAmountTransferDto accAmountTransferDto){
        BigDecimal updatedBalance  = amountService.depositAmount(accAmountTransferDto);
        return new ResponseEntity<>("Current Balance : " + updatedBalance,HttpStatus.OK);
    }


    @Operation(
            tags="Amount Controller",
            summary = "Withdraw Amount",
            description = "Withdraws amount from provided account"
    )
    @PatchMapping("/withdraw")
    public ResponseEntity<String> withdrawAmount(@RequestBody AccAmountTransferDto accAmountTransferDto){
        BigDecimal updatedBalance = amountService.withdrawAmount(accAmountTransferDto);
        return new ResponseEntity<>("Current Balance : " + updatedBalance,HttpStatus.OK);
    }



}
