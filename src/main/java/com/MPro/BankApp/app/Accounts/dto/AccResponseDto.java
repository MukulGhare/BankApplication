package com.MPro.BankApp.app.Accounts.dto;

import com.MPro.BankApp.app.Accounts.enums.AccountType;
import com.MPro.BankApp.app.Accounts.enums.CurrencyType;
import com.MPro.BankApp.app.Accounts.enums.StatusType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccResponseDto {

    private String ibanNo;
    private BigDecimal currentBalance;
    private CurrencyType currencyType;
    private AccountType accountType;
    private StatusType statusType;

}
