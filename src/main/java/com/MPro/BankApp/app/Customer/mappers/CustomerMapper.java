package com.MPro.BankApp.app.Customer.mappers;

import com.MPro.BankApp.app.Customer.Entity.Customer;
import com.MPro.BankApp.app.Customer.dto.CustomerSaveDto;
import com.MPro.BankApp.app.Customer.dto.ReturnCustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
//,unmappedTargetPolicy = ReportingPolicy.IGNORE
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    //CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer converToCustomer(CustomerSaveDto customerSaveDto);

    CustomerSaveDto convertToSaveDTo(Customer customer);

    ReturnCustomerDto convertToReturnCustomerDto (Customer customer);
}
