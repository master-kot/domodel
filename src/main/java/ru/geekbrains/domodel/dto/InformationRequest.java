package ru.geekbrains.domodel.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static ru.geekbrains.domodel.entities.constants.Messages.*;

/**
 * Запрос для создания Блока информации
 */
@Data
public class InformationRequest {

    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = TEXT_NOT_BLANK)
    private String text;

    @NotBlank(message = HIDDEN_NOT_BLANK)
    private boolean hidden;
}
