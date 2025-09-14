package com.MPro.BankApp.app.CreditCards.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCardDto {
    private BigDecimal maxLimit;
}
