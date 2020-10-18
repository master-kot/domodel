package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Messages.*;

/**
 * Dto представление сущности Обращение
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppealDto {

    private Long id;

    private String creationDate;

    @NotBlank(message = TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = TEXT_NOT_BLANK)
    private String text;

    @NotBlank(message = PHONE_NUMBER_NOT_BLANK)
    private String phoneNumber;

    @NotBlank(message = STATUS_NOT_BLANK)
    private String status;

    private Long authorId;

    private AuthorNameDto authorName;

    private List<String> photoLinks = new ArrayList<>();
}
