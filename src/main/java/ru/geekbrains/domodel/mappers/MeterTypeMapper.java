package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.MeterTypeDto;
import ru.geekbrains.domodel.entities.MeterType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeterTypeMapper {

    @Mappings({
            @Mapping(target = "typeId", source = "entity.id"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "measureUnit", source = "entity.measureUnit"),
    })
    MeterTypeDto meterTypeToMeterTypeDto(MeterType entity);

    List<MeterTypeDto> meterTypeToMeterTypeDto(List<MeterType> entities);

    @Mappings({
            @Mapping(target = "id", source = "dto.typeId"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "measureUnit", source = "dto.measureUnit"),
    })
    MeterType meterTypeDtoToMeterType(MeterTypeDto dto);
}
