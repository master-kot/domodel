package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Dto представление данных Фамилия, Имя и Отчество автора
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorNameDto {

    private String firstName;

    private String lastName;

    private String patronymic;
}
