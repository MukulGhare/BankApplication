package com.MPro.BankApp.app.CreditCards.mappers;

import com.MPro.BankApp.app.CreditCards.Entity.CreditCards;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    ReturnCardDetailsDto convertToReturnCard(CreditCards creditCards);
}
