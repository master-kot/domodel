package ru.geekbrains.domodel.dto;

import lombok.Data;

/**
 * DTO представление сущности Новость
 */
@Data
public class NewsDto {

    private Long id;

    // Дата создания
    private String creationDate;

    // Заголовок
    private String title;

    // Краткий текст новости
    private String shortText;

    // Полный текст новости
    private String fullText;

    // Ссылка на адрес картинки
    private String pictureLink;

    // Указатель публичности новости. Новость отображается для всех посетителей если true
    private boolean hidden;

    // Указатель закрепления новости. Новость закреплена если true
    private boolean pinned;

    // Указатель видимости новости. Новость отображается если true
    private boolean visible;

    // Автор
    private String authorName;
}
