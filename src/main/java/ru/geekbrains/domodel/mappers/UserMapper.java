package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.dto.UserRequest;
import ru.geekbrains.domodel.entities.User;
import ru.geekbrains.domodel.entities.common.JwtUser;

import java.util.List;

/**
 * Маппер, преобразующий классы User и UserDto друг в друга
 */
@Mapper(componentModel = "spring", uses = {AuthorityMapper.class})
public interface UserMapper {

    @Mappings({
            @Mapping(target="creationDate", source = "entity.creationDate", dateFormat = "dd-MM-yyyy")
    })
    UserDto userToUserDto(User entity);

    List<UserDto> userToUserDto(List<User> entities);

    JwtUser userToJwtUser(User entity);

    @Mappings({
            @Mapping(target="creationDate", source="dto.creationDate", dateFormat = "dd-MM-yyyy")
    })
    User userDtoToUser(UserDto dto);

    User userRequestToUser(UserRequest dto);

    @Mappings({
            @Mapping(target="id", ignore = true),
            @Mapping(target="username", ignore = true),
            @Mapping(target="password", ignore = true),
            @Mapping(target="authorities", ignore = true),
            @Mapping(target="creationDate", ignore = true),
    })
    User updateUser(@MappingTarget User entity, UserDto dto);
}
