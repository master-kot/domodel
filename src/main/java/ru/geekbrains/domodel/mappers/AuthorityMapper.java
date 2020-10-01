package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.common.JwtRole;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Authority в JwtRole
 */
@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    JwtRole authorityToJwtRole(Authority entity);

    List<JwtRole> authorityToJwtRole(List<Authority> entities);
}
