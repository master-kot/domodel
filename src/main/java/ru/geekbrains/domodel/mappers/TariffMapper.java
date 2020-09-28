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
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TariffMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="description", source = "entity.description"),
            @Mapping(target="price", source = "entity.price"),
            @Mapping(target="meterTypeDescription", source = "entity.meterType.description")
    })
    TariffDto tariffToTariffDto(Tariff entity);

    List<TariffDto> tariffToTariffDto(List<Tariff> entities);
}
