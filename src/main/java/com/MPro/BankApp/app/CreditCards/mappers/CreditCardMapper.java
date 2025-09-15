package com.MPro.BankApp.app.CreditCards.mappers;

import com.MPro.BankApp.app.CreditCards.Entity.CreditCards;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import com.MPro.BankApp.app.CreditCards.dto.UseCardDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    ReturnCardDetailsDto convertToReturnCard(CreditCards creditCards);
    CreditCards converUseCToCard(UseCardDto useCardDto);

}
