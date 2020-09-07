package ru.geekbrains.domodel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class MeterDto {

    private Long id;
    private String serialNumber;
    private String model;
    private LocalDate checkDate;

    private Long accountId;
    private String houseNumber;

    private String typeDescription;
    private String tariffDescription;

    private Double currentMeterData;
}
