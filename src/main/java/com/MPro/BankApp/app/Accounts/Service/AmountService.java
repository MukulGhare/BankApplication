package com.MPro.BankApp.app.Accounts.Service;

import com.MPro.BankApp.app.Accounts.Entity.Accounts;
import com.MPro.BankApp.app.Accounts.Repo.AccountsRepo;
import com.MPro.BankApp.app.Accounts.dto.AccAmountTransferDto;
import com.MPro.BankApp.app.Accounts.enums.StatusType;
import com.MPro.BankApp.app.Accounts.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class AmountService {

    @Autowired
    private AccountsRepo accountRepo;

    @Transactional
    public BigDecimal transferAmount(AccAmountTransferDto accAmountTransferDto)  {

        BigDecimal amount = accAmountTransferDto.getAmount();

        try {
            if (accAmountTransferDto.getTransactionType() != TransactionType.TRANSFER){
                throw new Exception("Use correct transaction type (TRANSFER)");
            }

            Accounts source = accountRepo.findById(accAmountTransferDto.getSourceID())
                    .orElseThrow(() -> new NoSuchElementException("Source Account Not Found "));

            Accounts destination = accountRepo.findById(accAmountTransferDto.getDestinationID())
                    .orElseThrow(()-> new NoSuchElementException("Destination Account Not Found"));


            if(source.getCurrentBalance().compareTo(amount) < 0){
                throw new Exception("Insufficient Balance :(");
            }

            if(source.getStatusType() == StatusType.PASSIVE){
                throw new Exception("Please Activate your account before transferring money !");

            }

            source.setCurrentBalance(source.getCurrentBalance().subtract(amount));
            destination.setCurrentBalance(destination.getCurrentBalance().add(amount));
            return source.getCurrentBalance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public BigDecimal depositAmount(AccAmountTransferDto accAmountTransferDto)  {
        BigDecimal amount = accAmountTransferDto.getAmount();
        Accounts source = accountRepo.findById(accAmountTransferDto.getSourceID())
                .orElseThrow(() -> new NoSuchElementException("Account not Found !"));

        try {
            if( source.getStatusType() != StatusType.PASSIVE ){
                source.setCurrentBalance(source.getCurrentBalance().add(amount));
                accountRepo.save(source);
            } else  throw new Exception("Please Activate your account before depositing money !");

        }   catch (Exception e) {
            throw new RuntimeException(e);
        }

        return  source.getCurrentBalance();
    }

    public BigDecimal withdrawAmount(AccAmountTransferDto accAmountTransferDto) {
        BigDecimal amount = accAmountTransferDto.getAmount();
        Accounts account = accountRepo.findById(accAmountTransferDto.getSourceID())
                .orElseThrow(() -> new NoSuchElementException("Account Not Found"));

        try {
            if (account.getStatusType() == StatusType.PASSIVE){
                throw new Exception("Please Activate your account before withdrawing money !");
            }
            if (account.getCurrentBalance().compareTo(amount) > 0){
                account.setCurrentBalance(account.getCurrentBalance().subtract(amount));
                accountRepo.save(account);
            } else throw new Exception("Insufficient Balance :(");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return account.getCurrentBalance();
    }
}
