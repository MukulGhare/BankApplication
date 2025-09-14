package com.MPro.BankApp.app.Customer.dto;

import lombok.Data;

@Data
public class CustomerSaveDto {

    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
