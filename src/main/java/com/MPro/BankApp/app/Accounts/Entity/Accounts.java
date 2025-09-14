package com.MPro.BankApp.app.Accounts.Entity;

import com.MPro.BankApp.app.Accounts.enums.AccountType;
import com.MPro.BankApp.app.Accounts.enums.CurrencyType;
import com.MPro.BankApp.app.Accounts.enums.StatusType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getIbanNo() {
        return ibanNo;
    }

    public void setIbanNo(String ibanNo) {
        this.ibanNo = ibanNo;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public LocalDate getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDate cancelDate) {
        this.cancelDate = cancelDate;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "ID=" + ID +
                ", customerID=" + customerID +
                ", ibanNo='" + ibanNo + '\'' +
                ", currentBalance=" + currentBalance +
                ", currencyType=" + currencyType +
                ", accountType=" + accountType +
                ", statusType=" + statusType +
                ", cancelDate=" + cancelDate +
                '}';
    }
}
