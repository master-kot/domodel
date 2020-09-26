package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.entities.Authority;
import ru.geekbrains.domodel.entities.common.JwtRole;

/**
 * Маппер, преобразовывающий классы Authority в JwtRole
 */
@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    @Mappings({
            @Mapping(target="authority", source = "entity.authority")
    })
    JwtRole authorityToJwtRole(Authority entity);
}
