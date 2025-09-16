package com.MPro.BankApp.app.CreditCards.Service;

import com.MPro.BankApp.app.Accounts.enums.StatusType;
import com.MPro.BankApp.app.CreditCards.Entity.CreditCards;
import com.MPro.BankApp.app.CreditCards.Repo.CreditCardRepo;
import com.MPro.BankApp.app.CreditCards.dto.CreateCardDto;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import com.MPro.BankApp.app.CreditCards.dto.UseCardDto;
import com.MPro.BankApp.app.CreditCards.mappers.CreditCardMapper;
import com.MPro.BankApp.app.Security.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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

    @Transactional
    public ReturnCardDetailsDto useCard(UseCardDto useCardDto) {
        BigDecimal amount = useCardDto.getAmount();

        CreditCards card = cardRepo.findByCardNo(useCardDto.getCardNo());

        if (isCardActive(card) && card.getCustomerId() == authService.getCurrentJwtUser()){
            //System.out.println(" Card is Active and with correct user");

            if(checkCardCredentials(useCardDto,card)){
                //System.out.println("Credentials OK");
                applyCharge(card,amount);
            }
        } else System.out.println("Card not found OR Card not Active");

        cardRepo.save(card);

        return cardMapper.convertToReturnCard(card);
    }

    private boolean checkCardCredentials(UseCardDto useCardDto, CreditCards card){

        if( useCardDto.getCardNo() != card.getCardNo()) return false;
        //System.out.println("getCardNo OK");


        if(!useCardDto.getExpireDate().equals(card.getExpireDate())) return false;
        //System.out.println("getExpireDate OK");

        if ( useCardDto.getCvv() != card.getCvv()) return false;
        //System.out.println("getCvv OK");

        return  true;
    }

    private void applyCharge(CreditCards useCard, BigDecimal amount){
        System.out.println("Card debt before " + useCard.getCurrentDebt());
        useCard.setAvailableLimit(useCard.getAvailableLimit().subtract(amount));
        useCard.setCurrentDebt(useCard.getCurrentDebt().add(amount));
        System.out.println("Card debt after " + useCard.getCurrentDebt());

    }

    private boolean isCardActive(CreditCards card) {
        return card.getCardStatusType() == StatusType.ACTIVE;
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


    public ReturnCardDetailsDto getCardById(int id) {
        int custID = authService.getCurrentJwtUser();
        CreditCards card = cardRepo.getReferenceById(id);
        if( card.getCustomerId() != custID){
            return  null;
        }
        return cardMapper.convertToReturnCard(card);
    }

    public List<ReturnCardDetailsDto> getAllCustomerCards() {
         List<CreditCards> cardsL = cardRepo.findByCustomerId(authService.getCurrentJwtUser());

         return cardMapper.convertToReturnList(cardsL);

    }

    public String cancelCard( UseCardDto useCardDto) {
        CreditCards card = cardRepo.findByCardNo(useCardDto.getCardNo());
        if( card.getCardStatusType() == StatusType.PASSIVE ){
            return "Card Already InActive";
        } else {
            card.setCardStatusType(StatusType.PASSIVE);
            card.setCancelDate(LocalDate.now());
            cardRepo.save(card);
            return  "Card marked as InActive";
        }
    }

    public ReturnCardDetailsDto payCard(UseCardDto useCardDto) {

        BigDecimal amount = useCardDto.getAmount();

        CreditCards card = cardRepo.findByCardNo(useCardDto.getCardNo());

        if (isCardActive(card) && card.getCustomerId() == authService.getCurrentJwtUser()){
            //System.out.println(" Card is Active and with correct user");
            card.setCurrentDebt(card.getCurrentDebt().subtract(amount));
            card.setAvailableLimit(card.getAvailableLimit().add(amount));
            cardRepo.save(card);

        } else System.out.println("Card not found OR Card not Active");

        return cardMapper.convertToReturnCard(card);

    }
}
