package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;
import ru.geekbrains.domodel.entities.Appeal;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Appeal и AppealDto друг в друга
 */
@Mapper(componentModel = "spring", uses = {PhotoLinkMapper.class, AppealStatusMapper.class})
public interface AppealMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="creationDate", source = "entity.creationDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target="title", source = "entity.title"),
            @Mapping(target="text", source = "entity.text"),
            @Mapping(target="phoneNumber", source = "entity.phoneNumber"),
            @Mapping(target="status", source = "entity.status"),
            @Mapping(target="authorId", source = "entity.author.id"),
            @Mapping(target="photoLinks", source = "entity.photoLinks")
    })
    AppealDto appealToAppealDto(Appeal entity);

    List<AppealDto> appealToAppealDto(List<Appeal> entities);

    @Mappings({
            @Mapping(target="title", source = "dto.title"),
            @Mapping(target="text", source = "dto.text"),
            @Mapping(target="phoneNumber", source = "dto.phoneNumber"),
            @Mapping(target="photoLinks", source = "dto.photoLinks")
    })
    Appeal appealRequestToAppeal(AppealRequest dto);

    @Mappings({
            @Mapping(target="id", ignore = true),
            @Mapping(target="creationDate", ignore = true),
            @Mapping(target="author", ignore = true),
            @Mapping(target="photoLinks", ignore = true)
    })
    Appeal updateAppeal(@MappingTarget Appeal entity, AppealDto dto);
}
