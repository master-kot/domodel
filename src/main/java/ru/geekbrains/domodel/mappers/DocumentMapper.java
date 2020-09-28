package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.entities.Document;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Document и DocumentDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="title", source = "entity.title"),
            @Mapping(target="linkAddress", source = "entity.linkAddress")
    })
    DocumentDto documentToDocumentDto(Document entity);

    List<DocumentDto> documentToDocumentDto(List<Document> entities);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="linkAddress", source="dto.linkAddress")
    })
    Document documentDtoToDocument(DocumentDto dto);
}
