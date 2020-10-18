package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static ru.geekbrains.domodel.entities.constants.Messages.LINK_ADDRESS_NOT_BLANK;

/**
 * Dto представление сущности Документ
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDto {

    private Integer id;

    private String title;

    @NotBlank(message = LINK_ADDRESS_NOT_BLANK)
    private String linkAddress;
}
