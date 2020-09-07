package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.MeterDto;
import ru.geekbrains.domodel.entities.Meter;

@Mapper(componentModel = "spring")
public interface MeterMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "serialNumber", source = "entity.serialNumber"),
            @Mapping(target = "model", source = "entity.model"),
            @Mapping(target = "checkDate", source = "entity.checkDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "accountId", source = "entity.account.id"),
            @Mapping(target = "houseNumber", source = "entity.account.houseNumber"),
            @Mapping(target = "typeDescription", source = "entity.type.description"),
            @Mapping(target = "tariffDescription", source = "entity.type.tariff.description")
    })
    MeterDto meterToMeterDto(Meter entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "serialNumber", source = "dto.serialNumber"),
            @Mapping(target = "model", source = "dto.model"),
            @Mapping(target = "checkDate", source = "dto.checkDate", dateFormat = "dd-MM-yyyy"),
    })
    MeterDto meterDtoToMeter(MeterDto dto);
}
