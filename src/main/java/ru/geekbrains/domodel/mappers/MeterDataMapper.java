package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.MeterDataDto;
import ru.geekbrains.domodel.entities.MeterData;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeterDataMapper {

    @Mappings({
            @Mapping(target = "meterDataId", source = "entity.id"),
            @Mapping(target = "value", source = "entity.value"),
            @Mapping(target = "creationDate", source = "entity.creationDate", dateFormat = "dd-MM-yyyy")

    })
    MeterDataDto meterDataToMeterDataDto(MeterData entity);

    List<MeterDataDto> meterDataToMeterDataDto(List<MeterData> entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.meterDataId"),
            @Mapping(target = "value", source = "dto.value"),
            @Mapping(target = "creationDate", source = "dto.creationDate", dateFormat = "dd-MM-yyyy"),
    })
    MeterData meterDataDtoToMeterData(MeterDataDto dto);
}
