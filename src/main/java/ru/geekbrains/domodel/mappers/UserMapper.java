package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.User;

/**
 * Маппер, преобразовывающий классы User и UserDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target="phone", source = "entity.username"),
            @Mapping(target="firstName", source = "entity.firstName"),
            @Mapping(target="lastName", source = "entity.lastName"),
            @Mapping(target="patronymic", source = "entity.patronymic"),
            @Mapping(target="email", source = "entity.email"),
            @Mapping(target="photoLink", source = "entity.photoLink"),
            @Mapping(target="address", source = "entity.address")})
    UserDto userToUserDto(User entity);

    @Mappings({
            @Mapping(target="username", source="dto.phone"),
            @Mapping(target="firstName", source="dto.firstName"),
            @Mapping(target="lastName", source="dto.lastName"),
            @Mapping(target="patronymic", source="dto.patronymic"),
            @Mapping(target="email", source="dto.email"),
            @Mapping(target="photoLink", source="dto.photoLink"),
            @Mapping(target="address", source="dto.address")})
    User userDtoToUser(UserDto dto);
}
