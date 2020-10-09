package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.entities.Document;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Document и DocumentDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentDto documentToDocumentDto(Document entity);

    List<DocumentDto> documentToDocumentDto(List<Document> entities);

    Document documentDtoToDocument(DocumentDto dto);

    default String documentToString(Document entity) {
        return entity.getLinkAddress();
    }
}
