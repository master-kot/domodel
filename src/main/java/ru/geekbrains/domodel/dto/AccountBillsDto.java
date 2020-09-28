package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Расширенное Dto представление сущности Лицевого счета.
 * Добавлен список платежей (счетов).
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBillsDto {

    private Long id;

    private String address;

    private String houseNumber;

    private Double acresAmount;

    private List<BillDto> bills;
}
