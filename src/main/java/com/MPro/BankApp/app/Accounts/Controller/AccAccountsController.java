package com.MPro.BankApp.app.Accounts.Controller;

import com.MPro.BankApp.app.Accounts.Entity.Accounts;
import com.MPro.BankApp.app.Accounts.Service.AccountsService;
import com.MPro.BankApp.app.Accounts.dto.AccResponseDto;
import com.MPro.BankApp.app.Accounts.dto.AccSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acc")
public class AccAccountsController {

    @Autowired
    private AccountsService accountsService;

    @Operation(
            tags = "Account Controller",
            summary = "Get an account",
            description = "Gets an account by id."
    )
    @GetMapping("/account/{id}")
    public AccResponseDto findAccountById(@PathVariable int id){
        return accountsService.findAccountById(id);
    }

    @Operation(
            tags = "Account Controller",
            summary = "Cancel Account",
            description = "Marks and Account as PASSIVE"
    )
    @PatchMapping("/account/{id}")
    public ResponseEntity<String> cancelAccount(@PathVariable int id){
        return accountsService.cancelAccount(id);
    }

    @Operation(
            tags= "Account Controller",
            summary = "Creats a new account",
            description = "Maps created account to authenticated Customer"
    )
    @PostMapping("/account")
    public AccResponseDto createAccount(@RequestBody AccSaveDto accSaveDto){
        return accountsService.createAccount(accSaveDto);
    }

    @Operation(
            tags= "Account Controller",
            summary = "Customer all Accounts",
            description = "All accounts logged in customer owns"
    )
    @GetMapping("/accounts")
    public List<AccResponseDto> getAllAccountsOfCustomer(){
        return accountsService.getAllAccountsOfCustomer();
    }

    @Operation(
            tags= "Account Controller",
            summary = "All Accounts",
            description = "All available accounts in bank"
    )
    @GetMapping("/accounts/all")
    public List<AccResponseDto> getAllAccounts(){
        return accountsService.getAllAccounts();
    }



}
