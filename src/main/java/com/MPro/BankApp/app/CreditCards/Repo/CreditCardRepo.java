package com.MPro.BankApp.app.CreditCards.Repo;

import com.MPro.BankApp.app.CreditCards.Entity.CreditCards;
import com.MPro.BankApp.app.CreditCards.dto.ReturnCardDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCards,Integer> {

    CreditCards findByCardNo(long cardNo);

    List<CreditCards> findByCustomerId(int customerId);
}
