package ru.geekbrains.domodel.dto;

import lombok.Data;

/**
 * Запрос для создания Блока информации
 */
@Data
public class InformationRequest {

    // Наименование блока
    private String title;

    // Текст блока
    private String text;

    private boolean hidden;
}
