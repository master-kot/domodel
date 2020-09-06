package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Запрос на создание нового Обращения
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppealRequest {

    // Заголовок (тема)
    private String title;

    // Полный текст обращения
    private String text;

    // Телефон для связи
    private String phoneNumber;

    // Список ссылок на фотографии
    private List<String> photoLinks = new ArrayList<>();
}
