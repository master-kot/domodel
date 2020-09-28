package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;
import ru.geekbrains.domodel.entities.Appeal;
import ru.geekbrains.domodel.entities.Information;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Information и InformationDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface InformationMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="title", source = "entity.title"),
            @Mapping(target="text", source = "entity.text"),
            @Mapping(target="hidden", source="entity.hidden")
    })
    InformationDto informationToInformationDto(Information entity);

    List<InformationDto> informationToInformationDto(List<Information> entities);

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="text", source="dto.text"),
            @Mapping(target="hidden", source="dto.hidden")
    })
    Information informationDtoToInformation(InformationDto dto);

    @Mappings({
            @Mapping(target="title", source="dto.title"),
            @Mapping(target="text", source="dto.text"),
            @Mapping(target="hidden", source="dto.hidden")
    })
    Information informationRequestToInformation(InformationRequest dto);

    @Mappings({
            @Mapping(target="id", ignore = true)
    })
    Information updateInformation(@MappingTarget Information entity, InformationDto dto);
}
