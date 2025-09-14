package com.MPro.BankApp.app.Accounts.Repo;

import com.MPro.BankApp.app.Accounts.Entity.Accounts;
import com.MPro.BankApp.app.Accounts.dto.AccResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts,Integer> {

    List<Accounts> findByCustomerID(int currentCustomerID);
}
