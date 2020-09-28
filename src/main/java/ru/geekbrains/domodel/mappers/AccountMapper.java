package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.dto.AccountMetersDto;
import ru.geekbrains.domodel.entities.Account;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Account и AccountDto друг в друга
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, MeterMapper.class})
public interface AccountMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="address", source = "entity.address"),
            @Mapping(target="houseNumber", source = "entity.houseNumber"),
            @Mapping(target="acresAmount", source = "entity.acresAmount"),
            @Mapping(target="user", source = "entity.user")
    })
    AccountDto accountToAccountDto(Account entity);

    List<AccountDto> accountToAccountDto(List<Account> entities);

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="address", source = "entity.address"),
            @Mapping(target="houseNumber", source = "entity.houseNumber"),
            @Mapping(target="acresAmount", source = "entity.acresAmount"),
            @Mapping(target="meters", source = "entity.meters")
    })
    AccountMetersDto accountToAccountMetersDto(Account entity);

    List<AccountMetersDto> accountToAccountMetersDto(List<Account> entities);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="address", source="dto.address"),
            @Mapping(target="houseNumber", source="dto.houseNumber"),
            @Mapping(target="acresAmount", source="dto.acresAmount"),
            @Mapping(target="user", source="dto.user")
    })
    Account accountDtoToAccount(AccountDto dto);
}
