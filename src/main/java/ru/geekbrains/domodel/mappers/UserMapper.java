package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.entities.User;

/**
 * Маппер, преобразующий классы User и UserDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="username", source = "entity.username"),
            @Mapping(target="firstName", source = "entity.firstName"),
            @Mapping(target="lastName", source = "entity.lastName"),
            @Mapping(target="patronymic", source = "entity.patronymic"),
            @Mapping(target="email", source = "entity.email"),
            @Mapping(target="photoLink", source = "entity.photoLink"),
            @Mapping(target="address", source = "entity.address"),
            @Mapping(target="phoneNumber", source = "entity.phoneNumber")})
    UserDto userToUserDto(User entity);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="username", source="dto.username"),
            @Mapping(target="firstName", source="dto.firstName"),
            @Mapping(target="lastName", source="dto.lastName"),
            @Mapping(target="patronymic", source="dto.patronymic"),
            @Mapping(target="email", source="dto.email"),
            @Mapping(target="photoLink", source="dto.photoLink"),
            @Mapping(target="address", source="dto.address"),
            @Mapping(target="phoneNumber", source="dto.phoneNumber")})
    User userDtoToUser(UserDto dto);
}
