package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.Account;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.common.JwtRole;
import ru.geekbrains.domodel.entities.common.JwtUser;
import ru.geekbrains.domodel.entities.common.UserCommon;

import java.util.stream.Collectors;

/**
 * Маппер, преобразовывающий классы User и UserDto друг в друга
 */
@Mapper(componentModel = "spring")
public abstract class UserCommonMapper {
    
    private AuthorityMapper authorityMapper;

    public static UserCommon userToUserCommon(User entity) {
        UserCommon user = new UserCommon();
        user.setUsername(entity.getUsername());
        user.setRoles(entity.getAuthorities()
                .stream().map(Authority::getAuthority).collect(Collectors.toList()));
        user.setAccountIds(entity.getAccounts()
                .stream().map(Account::getId).collect(Collectors.toList()));
        return user;
    }

    public static JwtUser userToJwtUser(User entity) {
        JwtUser user = new JwtUser(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.isEnabled(), 
                entity.getAuthorities().stream()
                        .map(auth -> new JwtRole(auth.getAuthority())).collect(Collectors.toList()),
                entity.getAccounts().stream()
                        .map(Account::getId).collect(Collectors.toList()));
        return user;
    }
}
