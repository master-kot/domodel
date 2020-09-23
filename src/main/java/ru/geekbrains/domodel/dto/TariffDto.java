package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Dto представление сущности Тарифа
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TariffDto {

}
