package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.AccountMetersDto;
import ru.geekbrains.domodel.dto.AccountRequest;
import ru.geekbrains.domodel.entities.Account;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Account и AccountDto друг в друга
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, MeterMapper.class})
public interface AccountMapper {

    AccountDto accountToAccountDto(Account entity);

    List<AccountDto> accountToAccountDto(List<Account> entities);

    AccountMetersDto accountToAccountMetersDto(Account entity);

    List<AccountMetersDto> accountToAccountMetersDto(List<Account> entities);

    Account accountDtoToAccount(AccountDto dto);

    Account accountRequestToAccount(AccountRequest accountRequest);

    @Mappings({
            @Mapping(target="id", ignore = true),
            @Mapping(target="user", ignore = true)
    })
    Account updateAccount(@MappingTarget Account entity, AccountDto dto);
}
