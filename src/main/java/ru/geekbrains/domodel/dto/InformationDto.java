package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO сущность единичного блока информации о компании
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InformationDto {

    private Integer id;

    // Наименование блока
    private String title;

    // Текст блока
    private String text;

    private boolean hidden;
}
