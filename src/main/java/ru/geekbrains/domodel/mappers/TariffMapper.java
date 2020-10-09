package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.TariffDto;
import ru.geekbrains.domodel.entities.Tariff;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Tariff и TariffDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface TariffMapper {

    @Mappings({
            @Mapping(target="tariffId", source = "entity.id"),
            @Mapping(target="description", source = "entity.description"),
            @Mapping(target = "price", source = "entity.price")
    })
    TariffDto tariffToTariffDto(Tariff entity);

    List<TariffDto> tariffToTariffDto(List<Tariff> entities);

    @Mappings({
            @Mapping(target="id", source = "dto.tariffId"),
            @Mapping(target="description", source = "dto.description"),
            @Mapping(target = "price", source = "dto.price")
    })
    Tariff tariffDtoToTariff(TariffDto dto);
}
