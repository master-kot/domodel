package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.common.JwtRole;
import ru.geekbrains.domodel.entities.common.JwtUser;

import java.util.stream.Collectors;

/**
 * Маппер, преобразовывающий классы User и JwtUser друг в друга
 */
@Mapper(componentModel = "spring")
public abstract class JwtUserMapper {

    public JwtUser userToJwtUser(User entity) {
        JwtUser user = new JwtUser();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEnabled(entity.isEnabled());
        user.setAuthorities(entity.getAuthorities().stream()
                        .map(auth -> new JwtRole(auth.getAuthority())).collect(Collectors.toList()));
        return user;
    }
}
