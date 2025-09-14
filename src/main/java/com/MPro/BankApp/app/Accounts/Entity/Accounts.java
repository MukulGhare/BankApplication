package com.MPro.BankApp.app.Accounts.Entity;

import com.MPro.BankApp.app.Accounts.enums.AccountType;
import com.MPro.BankApp.app.Accounts.enums.CurrencyType;
import com.MPro.BankApp.app.Accounts.enums.StatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name="Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name="customerid")
    private int customerID;

    @Column(name="ibanno")
    private String ibanNo;

    @Column(name="currentbalance")
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name="currencytype")
    private CurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    @Column(name="accounttype")
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name="statustype")
    private StatusType statusType;

    @Column(name="canceldate")
    private LocalDate cancelDate;


}
