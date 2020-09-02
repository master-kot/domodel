package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.AccountDto;
import ru.geekbrains.domodel.entities.Account;

/**
 * Маппер, преобразовывающий классы Account и AccountDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="address", source = "entity.address"),
            @Mapping(target="houseNumber", source = "entity.houseNumber"),
            @Mapping(target="acresAmount", source = "entity.acresAmount")})
    AccountDto accountToAccountDto(Account entity);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="address", source="dto.address"),
            @Mapping(target="houseNumber", source="dto.houseNumber"),
            @Mapping(target="acresAmount", source="dto.acresAmount")})
    Account accountDtoToAccount(AccountDto dto);
}
