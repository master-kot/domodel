package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.geekbrains.domodel.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO представление сущности Обращение
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppealDto {

    private Long id;

    // Дата создания
    private String creationDate;

    // Заголовок (тема)
    private String title;

    // Полный текст обращения
    private String text;

    // Телефон для связи
    private String phoneNumber;

    // Статус выполнения обращения
    private String status;

    // Пользователь - автор обращения
    private User authorId;

    // Список ссылок на фотографии
    private List<String> photoLinks = new ArrayList<>();
}
