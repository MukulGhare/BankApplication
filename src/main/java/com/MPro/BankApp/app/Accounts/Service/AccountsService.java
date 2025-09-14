package com.MPro.BankApp.app.Accounts.Service;

import com.MPro.BankApp.app.Accounts.Entity.Accounts;
import com.MPro.BankApp.app.Accounts.Repo.AccountsRepo;
import com.MPro.BankApp.app.Accounts.dto.AccResponseDto;
import com.MPro.BankApp.app.Accounts.dto.AccSaveDto;
import com.MPro.BankApp.app.Accounts.enums.CurrencyType;
import com.MPro.BankApp.app.Accounts.enums.StatusType;
import com.MPro.BankApp.app.Accounts.mappers.AccountsMapper;
import com.MPro.BankApp.app.Security.Service.AuthService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsService {

    @Autowired
    private AccountsMapper mapper;

    @Autowired
    private AccountsRepo repo;

    @Autowired
    private AuthService authService;

    public AccResponseDto findAccountById(int id) {
        Optional<Accounts> account = repo.findById(id);
        return mapper.convertToAccResponseDto(account.get());

    }

    public ResponseEntity<String> cancelAccount(int id) {
        Accounts account = repo.findById(id).get();
        if(account.getStatusType() == StatusType.PASSIVE){
            return ResponseEntity.ok("Account : "+ account.getIbanNo() + " is closed on " + account.getCancelDate());

        }
        account.setStatusType(StatusType.PASSIVE);
        account.setCancelDate(LocalDate.now());
        repo.save(account);
        return ResponseEntity.ok("Account : "+ account.getIbanNo() + " marked as PASSIVE");
    }

    public List<AccResponseDto> getAllAccountsOfCustomer() {
        int currentCustomerID = authService.getCurrentJwtUser();
        List<Accounts> accountsList= repo.findByCustomerID(currentCustomerID);
        return mapper.convertToResponseList(accountsList);
    }

    public List<AccResponseDto> getAllAccounts() {
        return mapper.convertToResponseList(repo.findAll());
    }


    public AccResponseDto createAccount(AccSaveDto accSaveDto) {
        // get authenticated customer
        int currentCustomerID = authService.getCurrentJwtUser();

        // convert DTO -> Acc entity
        Accounts newAccount = mapper.convertToAccEntity(accSaveDto);

        // update customerID for mapping
        newAccount.setCustomerID(currentCustomerID);

        // update any other required fields
        CurrencyType currencyType = accSaveDto.getCurrencyType();
        newAccount.setCurrencyType(currencyType);
        newAccount.setAccountType(accSaveDto.getAccountType());
        newAccount.setCurrentBalance(accSaveDto.getCurrentBalance());
        newAccount.setIbanNo(getIbanNo(currencyType));
        newAccount.setStatusType(StatusType.ACTIVE);

        //Add new account in repo
        System.out.println(newAccount);
        repo.save(newAccount);

        // return responseDto
        return mapper.convertToAccResponseDto(newAccount);
    }

    private String getIbanNo(CurrencyType currencyType) {
        String ibanNo = "";
        ibanNo = getCountryCode(currencyType.toString())
                +RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphanumeric(16);
        ibanNo = addSpaces(ibanNo).toUpperCase();

        //System.out.println("Final Iban -->  "  + ibanNo);
        return ibanNo;
    }

    private String addSpaces(String ibanNo) {
        StringBuilder formatted = new StringBuilder();
        for(int i=0; i<ibanNo.length();i++){
            if( i>0 && i%4==0){
                formatted.append(" ");
            }
            formatted.append(ibanNo.charAt(i));
        }
        return  formatted.toString();
    }

    private String getCountryCode(String currency){
        return switch (currency.toUpperCase()) {
            case "EU" -> "EU";
            case "USD" -> "US";
            case "INR" -> "IN";
            default -> throw new IllegalArgumentException("Unsupported currency: " + currency);
        };
    }



}
