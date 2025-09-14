package com.MPro.BankApp.app.Accounts.dto;

import com.MPro.BankApp.app.Accounts.enums.TransactionType;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class AccAmountTransferDto {

    @NonNull
    @Min(value = 1, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NonNull
    private TransactionType transactionType;

    @NonNull
    private int sourceID;
    private int destinationID;

}
