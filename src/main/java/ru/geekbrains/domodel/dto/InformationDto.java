package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static ru.geekbrains.domodel.entities.constants.Messages.*;

/**
 * DTO представление сущности Блок информации
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InformationDto {

    @NotBlank(message = DATA_NOT_BLANK + "Индекс")
    private Integer id;

    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = TEXT_NOT_BLANK)
    private String text;

    @NotNull(message = HIDDEN_NOT_BLANK)
    private boolean hidden;
}
