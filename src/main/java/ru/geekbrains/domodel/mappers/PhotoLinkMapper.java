package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.PhotoLinkDto;
import ru.geekbrains.domodel.entities.PhotoLink;

import java.util.List;

/**
 * Маппер, преобразовывающий классы PhotoLink и PhotoLinkDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface PhotoLinkMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="title", source = "entity.title"),
            @Mapping(target="linkAddress", source = "entity.linkAddress")
    })
    PhotoLinkDto photoLinkToPhotoLinkDto(PhotoLink entity);

    List<PhotoLinkDto> photoLinkToPhotoLinkDto(List<PhotoLink> entities);

    default String photoLinkToString(PhotoLink entity) {
        return entity.getLinkAddress();
    }

    List<String> photoLinkToString(List<PhotoLink> entities);
}
