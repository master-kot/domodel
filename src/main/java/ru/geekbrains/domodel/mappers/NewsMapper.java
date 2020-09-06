package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequestDto;
import ru.geekbrains.domodel.entities.News;

/**
 * Маппер, преобразовывающий классы News и NewsDto друг в друга
 */
@Mapper(componentModel = "spring")
@Component
public interface NewsMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="creationDate", source = "entity.creationDate",
                    dateFormat = "dd-MM-yyyy"),
            @Mapping(target="title", source = "entity.title"),
            @Mapping(target="shortText", source = "entity.fullText"),
            @Mapping(target="fullText", source = "entity.fullText"),
            @Mapping(target="pictureLink", source = "entity.pictureLink"),
            @Mapping(target="hidden", source = "entity.hidden"),
            @Mapping(target="pinned", source = "entity.pinned"),
            @Mapping(target="visible", source = "entity.visible"),
            @Mapping(target="authorName", source = "entity.authorId.firstName")})
    NewsDto newsToNewsDto(News entity);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="creationDate", source="dto.creationDate",
                    dateFormat="dd-MM-yyyy"),
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="fullText", source="dto.fullText"),
            @Mapping(target="pictureLink", source="dto.pictureLink"),
            @Mapping(target="hidden", source="dto.hidden"),
            @Mapping(target="pinned", source="dto.pinned"),
            @Mapping(target="visible", source="dto.visible")})
    News newsDtoToNews(NewsDto dto);

    @Mappings({
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="fullText", source="dto.fullText"),
            @Mapping(target="pictureLink", source="dto.photoLink"),
            @Mapping(target="hidden", source="dto.hidden"),
            @Mapping(target="pinned", source="dto.pinned"),
            @Mapping(target="visible", source="dto.visible")})
    News newsRequestDtoToNews(NewsRequestDto dto);
}
