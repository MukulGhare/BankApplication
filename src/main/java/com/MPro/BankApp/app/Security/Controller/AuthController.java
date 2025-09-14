package com.MPro.BankApp.app.Security.Controller;

import com.MPro.BankApp.app.Customer.dto.CustomerSaveDto;
import com.MPro.BankApp.app.Customer.dto.ReturnCustomerDto;
import com.MPro.BankApp.app.Security.Service.AuthService;
import com.MPro.BankApp.app.Security.dto.CustomerLoginDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ReturnCustomerDto register(@RequestBody CustomerSaveDto customerSaveDto){

        return service.register(customerSaveDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody CustomerLoginDto customerLoginDto){
        return service.login(customerLoginDto);
    }
}
