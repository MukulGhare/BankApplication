package com.MPro.BankApp.app.Accounts.dto;

import com.MPro.BankApp.app.Accounts.enums.AccountType;
import com.MPro.BankApp.app.Accounts.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccSaveDto {

    private BigDecimal currentBalance;
    private CurrencyType currencyType;
    private AccountType accountType;

}
