package ru.geekbrains.domodel.mappers;

import org.springframework.stereotype.Component;
import ru.geekbrains.domodel.dto.CalculationDto;
import ru.geekbrains.domodel.entities.Calculation;

@Component
public class CalculationMapper {

    public static CalculationDto calculationToCalculationDto(Calculation entity) {
        CalculationDto dto = new CalculationDto();
        dto.setAmount(entity.getAmount());
        dto.setPrice(entity.getPrice());
        dto.setCost(entity.getCost());
        dto.setPreviousData(entity.getPreviousData());
        dto.setCurrentData(entity.getCurrentData());
        return dto;
    }

    public static Calculation calculationDtoToCalculation(CalculationDto dto) {
        Calculation entity = new Calculation();
        entity.setAmount(dto.getAmount());
        entity.setPrice(dto.getPrice());
        entity.setCost(dto.getCost());
        entity.setPreviousData(dto.getPreviousData());
        entity.setCurrentData(dto.getCurrentData());
        return entity;
    }
}
