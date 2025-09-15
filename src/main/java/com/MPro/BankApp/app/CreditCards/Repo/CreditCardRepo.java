package com.MPro.BankApp.app.CreditCards.Repo;

import com.MPro.BankApp.app.CreditCards.Entity.CreditCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCards,Integer> {

    CreditCards findByCardNo(long cardNo);
}
