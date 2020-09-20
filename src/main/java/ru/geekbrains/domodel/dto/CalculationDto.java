package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.geekbrains.domodel.entities.MeterData;

/**
 * Dto представление сущности калькуляции для каждой конкретной строки.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculationDto {

    private Long id;

    // Количество единиц - разница паказаний
    private Double amount;

    // Цена за единицу
    private Double price;

    // Стоимость за все потребленные единицы
    private Double cost;

    // Ссылка на предыдущие показания счетчика
    private MeterData previousData;

    // Ссылка на текущие показания счетчика
    private MeterData currentData;
}
