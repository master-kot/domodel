package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO представление сущности Лицевой счет
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto implements Dto {

    private Long id;

    private String address;

    private String houseNumber;

    private Double acresAmount;
}
