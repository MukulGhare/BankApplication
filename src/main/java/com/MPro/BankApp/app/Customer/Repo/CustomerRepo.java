package com.MPro.BankApp.app.Customer.Repo;

import com.MPro.BankApp.app.Customer.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Customer findByIdentityNo(Long identityNo);
}
