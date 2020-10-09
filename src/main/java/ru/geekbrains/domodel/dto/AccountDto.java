package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Базовое Dto представление сущности Лицевого счета.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto {

    private Long id;

    private String address;

    private String houseNumber;

    private Double acresAmount;

    private UserDto user;
}
