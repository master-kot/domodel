package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.RoleDto;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.common.JwtRole;

/**
 * Маппер, преобразовывающий классы Authority в RoleDto
 */
@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Mappings({
            @Mapping(target="role", source = "entity.authority")})
    RoleDto authorityToRoleDto(Authority entity);

    @Mappings({
            @Mapping(target="authority", source = "entity.authority")})
    JwtRole authorityToJwtRole(Authority entity);
}
