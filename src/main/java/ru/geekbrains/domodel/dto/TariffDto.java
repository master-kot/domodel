package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Dto представление сущности Тарифа
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TariffDto {

    @ApiModelProperty(value = "id тарифа")
    private Integer tariffId;

    @ApiModelProperty(value = "Тарифф")
    private String description;

    @ApiModelProperty(value = "Ценна тариффа")
    private Double price;
}
