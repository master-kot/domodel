package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterDto {

    private Long id;

    private String serialNumber;

    private String model;

    private LocalDate checkDate;

    private Long accountId;

    private String houseNumber;

    private String typeDescription;

    private String tariffDescription;

    private MeterDataDto currentMeterData;
}
