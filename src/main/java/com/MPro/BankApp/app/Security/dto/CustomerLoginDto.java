package com.MPro.BankApp.app.Security.dto;

import lombok.Data;

@Data
public class CustomerLoginDto {
    private Long identityNo;
    private String password;
}
