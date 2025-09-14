package com.MPro.BankApp.app.Accounts.mappers;

import com.MPro.BankApp.app.Accounts.Entity.Accounts;
import com.MPro.BankApp.app.Accounts.dto.AccResponseDto;
import com.MPro.BankApp.app.Accounts.dto.AccSaveDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountsMapper  {
    AccResponseDto convertToAccResponseDto(Accounts accounts);
    Accounts convertToAccEntity(AccSaveDto accSaveDto);
    List<AccResponseDto> convertToResponseList(List<Accounts> accountsList);
}
