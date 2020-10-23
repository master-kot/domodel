package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Messages.*;

/**
 * Запрос на создание нового Обращения
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppealRequest {

    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = TEXT_NOT_BLANK)
    private String text;

    @NotBlank(message = PHONE_NUMBER_NOT_BLANK)
    private String phoneNumber;

    private List<String> photoLinks = new ArrayList<>();
}
