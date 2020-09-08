package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Dto представление сущности Ссылка на фотографию
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoLinkDto {

    private Integer id;

    private String title;

    private String linkAddress;
}
