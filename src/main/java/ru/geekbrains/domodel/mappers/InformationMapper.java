package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;
import ru.geekbrains.domodel.entities.Information;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Information и InformationDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface InformationMapper {

    InformationDto informationToInformationDto(Information entity);

    List<InformationDto> informationToInformationDto(List<Information> entities);

    Information informationDtoToInformation(InformationDto dto);

    @Mappings({
            @Mapping(target="id", ignore = true),
    })
    Information informationRequestToInformation(InformationRequest dto);

    @Mappings({
            @Mapping(target="id", ignore = true)
    })
    Information updateInformation(@MappingTarget Information entity, InformationDto dto);
}
