package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.entities.Requisites;

/**
 * Маппер, преобразовывающий классы Requisites и RequisitesDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface RequisitesMapper {

    RequisitesDto requisitesToRequisitesDto(Requisites entity);

    @Mappings({
            @Mapping(target="id", ignore = true)
    })
    Requisites requisitesDtoToRequisites(RequisitesDto dto);

    @Mappings({
            @Mapping(target="id", ignore = true)
    })
    Requisites updateRequisites(@MappingTarget Requisites entity, RequisitesDto dto);
}
