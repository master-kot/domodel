package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Dto представление сущности Платежа
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDto {

    private Long id;

    // Дата создания
    private String creationDate;

    // Назначение платежа
    private String target;

    // Сумма к оплате (сумма всех калькуляций)
    private Double total;

    // Тип платежа
    private String type;

    // Статус платежа, изменяется председателем (бухгалтером)
    private boolean paymentStatus;

    // Номер лицевого счета
    private Long account;

    // Номер платежных реквизитов
    private Integer requisites;

    // Список калькуляций (обоснование цены счета)
    private List<CalculationDto> calculations = new ArrayList<>();
}
