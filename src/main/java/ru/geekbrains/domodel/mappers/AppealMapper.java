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
            @Mapping(target="authorId", source = "entity.author.id")
    })
    AppealDto appealToAppealDto(Appeal entity);

    List<AppealDto> appealToAppealDto(List<Appeal> entities);

    Appeal appealRequestToAppeal(AppealRequest dto);

    @Mappings({
            @Mapping(target="id", ignore = true),
            @Mapping(target="creationDate", ignore = true),
            @Mapping(target="author", ignore = true),
            @Mapping(target="photoLinks", ignore = true)
    })
    Appeal updateAppeal(@MappingTarget Appeal entity, AppealDto dto);
}
