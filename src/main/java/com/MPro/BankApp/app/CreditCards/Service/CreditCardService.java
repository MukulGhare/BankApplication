package com.MPro.BankApp.app.CreditCards.Service;

import com.MPro.BankApp.app.Accounts.enums.StatusType;
import com.MPro.BankApp.app.CreditCards.Entity.CreditCards;
import com.MPro.BankApp.app.CreditCards.Repo.CreditCardRepo;
import com.MPro.BankApp.app.CreditCards.dto.CreateCardDto;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import com.MPro.BankApp.app.CreditCards.mappers.CreditCardMapper;
import com.MPro.BankApp.app.Security.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepo cardRepo ;

    @Autowired
    private CreditCardMapper cardMapper;

    @Autowired
    private AuthService authService;

    public ReturnCardDetailsDto addCard(CreateCardDto createCardDto) {

        CreditCards card = createNewCreditCard(createCardDto); //creates new Card with all attributes

        cardRepo.save(card);
        return cardMapper.convertToReturnCard(card);
    }

    private CreditCards createNewCreditCard(CreateCardDto maxLimit){
        CreditCards newCard = new CreditCards();

        newCard.setCustomerId(authService.getCurrentJwtUser());
        newCard.setCardNo(createCardNo());
        newCard.setExpireDate(LocalDate.now().plusYears(10));
        newCard.setCvv(new Random().nextInt(900));
        newCard.setMaxLimit(maxLimit.getMaxLimit());
        newCard.setAvailableLimit(maxLimit.getMaxLimit());
        newCard.setCurrentDebt(BigDecimal.valueOf(0.00));
        newCard.setMinPaymentAmount(BigDecimal.valueOf(0.00));
        newCard.setCardStatusType(StatusType.ACTIVE);

        return  newCard;
    }

    private long createCardNo(){
        Random random = new Random();
        long min = 1_000_000_000_000_000L;
        long max = 10_000_000_000_000_000L;

         return  random.nextLong(min,max);
    }


}
