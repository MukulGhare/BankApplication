package com.MPro.BankApp.app.Security.Service;


import com.MPro.BankApp.app.Customer.Service.CustomerService;
import com.MPro.BankApp.app.Customer.dto.CustomerSaveDto;
import com.MPro.BankApp.app.Customer.dto.ReturnCustomerDto;
import com.MPro.BankApp.app.Security.dto.CustomerLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class AuthService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public ReturnCustomerDto register(CustomerSaveDto customerSaveDto) {
        return customerService.register(customerSaveDto);
    }

    public ResponseEntity login(CustomerLoginDto customerLoginDto) {

        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(customerLoginDto.getIdentityNo(),customerLoginDto.getPassword()));

        if( auth.isAuthenticated()){
            String token = jwtService.generateToken(customerLoginDto.getIdentityNo());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).build();

    }

    public int getCurrentJwtUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        MyUserDetails currentUser = null;
        if( auth != null){
            currentUser = (MyUserDetails) auth.getPrincipal();
        }
        return  currentUser.getID();
    }
}
