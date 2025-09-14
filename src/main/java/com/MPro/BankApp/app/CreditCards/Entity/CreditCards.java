package com.MPro.BankApp.app.CreditCards.Entity;

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
@Table(name="creditcards")
public class CreditCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="customer_id")
    private int customerId;

    @Column(name="card_no")
    private long cardNo;

    @Column(name="expire_date")
    private LocalDate expireDate;

    private int cvv;

    @Column(name="max_limit")
    private BigDecimal maxLimit;

    @Column(name="available_limit")
    private BigDecimal availableLimit;

    @Column(name="current_debt")
    private BigDecimal currentDebt;


    @Column(name="min_payment_amount")
    private BigDecimal minPaymentAmount;

    @Column(name="cut_off_date")
    private LocalDate cutOffDate;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name="card_status_type")
    private StatusType cardStatusType;

    @Column(name="cancel_date")
    private LocalDate cancelDate;

}
