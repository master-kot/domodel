package ru.geekbrains.domodel.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MeterDataDto {

    private Long id;
    private LocalDate creationDate;
    private Double value;
}
