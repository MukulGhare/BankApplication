package com.MPro.BankApp.app.Customer.Service;

import com.MPro.BankApp.app.Customer.Entity.Customer;
import com.MPro.BankApp.app.Customer.Repo.CustomerRepo;
import com.MPro.BankApp.app.Customer.dto.CustomerSaveDto;
import com.MPro.BankApp.app.Customer.dto.ReturnCustomerDto;
import com.MPro.BankApp.app.Customer.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerRepo repo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    public ReturnCustomerDto register(CustomerSaveDto customerSaveDto) {
        Customer cust = mapper.converToCustomer(customerSaveDto);
        cust.setPassword( passwordEncoder.encode(cust.getPassword()));
        cust = repo.save(cust);

        return mapper.convertToReturnCustomerDto(cust);
    }


    public ReturnCustomerDto updateCustomer(CustomerSaveDto customerSaveDto) {
        Customer custom = repo.findByIdentityNo(customerSaveDto.getIdentityNo());

        if(custom.getID() != 0){
            custom.setName(customerSaveDto.getName());
            custom.setSurname(customerSaveDto.getSurname());
            custom.setIdentityNo(customerSaveDto.getIdentityNo());
            custom.setPassword(passwordEncoder.encode(customerSaveDto.getPassword()));
        }

        custom = repo.save(custom);
        return mapper.convertToReturnCustomerDto(custom);

    }

    public ResponseEntity deleteCustomer(Long identityNo) {
        Customer customer = repo.findByIdentityNo(identityNo);
        if( customer == null){
            return new ResponseEntity("Incorrect ID, Customer not found", HttpStatus.NO_CONTENT);
        }
        repo.delete(customer);
        return new ResponseEntity<>(customer.getName()+" Account Deleted",HttpStatus.OK);
    }

    public ReturnCustomerDto getCustomerByIdentityNo(Long identityNo) {
        Customer cust = repo.findByIdentityNo(identityNo);
        return mapper.convertToReturnCustomerDto(cust);
    }
}
