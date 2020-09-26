package ru.geekbrains.domodel.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.domodel.dto.CalculationDto;
import ru.geekbrains.domodel.entities.Calculation;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Calculation и CalculationDto друг в друга
 */
@Mapper(componentModel = "spring")
public interface CalculationMapper {

    @Mappings({
            @Mapping(target="id", source = "entity.id"),
            @Mapping(target="amount", source = "entity.amount"),
            @Mapping(target="price", source = "entity.price"),
            @Mapping(target="cost", source = "entity.cost"),
            @Mapping(target="previousDataValue", source = "entity.previousData.value"),
            @Mapping(target="currentDataValue", source = "entity.currentData.value")
    })
    CalculationDto calculationToCalculationDto(Calculation entity);

    List<CalculationDto> calculationToCalculationDto(List<Calculation> entities);
}
