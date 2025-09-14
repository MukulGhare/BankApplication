package com.MPro.BankApp.app.Customer.Controller;

import com.MPro.BankApp.app.Customer.Service.CustomerService;
import com.MPro.BankApp.app.Customer.dto.CustomerSaveDto;
import com.MPro.BankApp.app.Customer.dto.ReturnCustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cus")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(
            tags="Customer Controller",
            summary = "Welcome message",
            description = "Greetings to Customer on homepage"
    )
    @GetMapping("/greet")
    public String greet(){
        System.out.println("Welcome to MProBank !");
        return "Welcome to MProBank !";
    }


    @Operation(
            tags="Customer Controller",
            summary = "Get Customer Profile",
            description = "Name, Surname, IdentityNo is visible"
    )
    @GetMapping("/customer/{identityNo}")
    public ReturnCustomerDto getCustomerByIdentityNo(@PathVariable Long identityNo){
        return customerService.getCustomerByIdentityNo(identityNo);
    }

    @Operation(
            tags="Customer Controller",
            summary = "Update Customer",
            description = "Name, Surname, IdentityNo & Password can be updated"
    )
    @PutMapping("/update-profile")
    public ReturnCustomerDto updateCustomer(@RequestBody CustomerSaveDto customerSaveDto){
        return  customerService.updateCustomer(customerSaveDto);
    }


    @Operation(
            tags="Customer Controller",
            summary = "Delete Customer",
            description = "Deletes customer from bank server"
    )
    @DeleteMapping("/delete-profile/{identityNo}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long identityNo ){
        return customerService.deleteCustomer(identityNo);
    }




}
