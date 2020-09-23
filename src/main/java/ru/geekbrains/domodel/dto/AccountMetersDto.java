package ru.geekbrains.domodel.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountMetersDto {

    private Long id;
    private String address;
    private String houseNumber;
    private Double acresAmount;

    private List<MeterDto> meters;
}
