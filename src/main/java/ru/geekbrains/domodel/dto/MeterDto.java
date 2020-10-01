package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterDto {

    @ApiModelProperty(value = "id счетчика")
    private Long meterId;

    @ApiModelProperty(value = "Серийный номер счетчика")
    private String serialNumber;

    @ApiModelProperty(value = "Модель счетчика")
    private String model;

    @ApiModelProperty(value = "Дата проверки счетчика")
    private LocalDate checkDate;

    @ApiModelProperty(value = "id лицевого счета")
    private Long accountId;

    @ApiModelProperty(value = "Номер дома/участка")
    private String houseNumber;

    @ApiModelProperty(value = "Тип счетчика")
    private String typeDescription;

    @ApiModelProperty(value = "Тарифф счетчика")
    private String tariffDescription;

    @ApiModelProperty(value = "Последние поданные показания по счетчику")
    private MeterDataDto currentMeterData;
}
