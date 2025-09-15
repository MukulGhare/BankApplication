package com.MPro.BankApp.app.CreditCards.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NotNull
public class UseCardDto {
    private BigDecimal amount;
    private long cardNo;
    private LocalDate expireDate;
    private int cvv;
}
