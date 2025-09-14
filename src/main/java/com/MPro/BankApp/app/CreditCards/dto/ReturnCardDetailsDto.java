package com.MPro.BankApp.app.CreditCards.dto;

import com.MPro.BankApp.app.Accounts.enums.StatusType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReturnCardDetailsDto {
    private long cardNo;
    private LocalDate expireDate;
    private int cvv;
    private BigDecimal maxLimit;
    private BigDecimal availableLimit;
    private BigDecimal currentDebt;
    private BigDecimal minPaymentAmount;
    private LocalDate cutOffDate;
    private LocalDate dueDate;
    private StatusType cardStatusType;
}
