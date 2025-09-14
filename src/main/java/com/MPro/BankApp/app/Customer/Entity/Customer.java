package com.MPro.BankApp.app.Customer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String name;
    private String surname;

    @Column(name = "identityno")
    private Long identityNo;
    private String password;

}
