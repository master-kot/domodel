package ru.geekbrains.domodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Запрос для создания Новости
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsRequest {

    // Заголовок
    private String title;

    // Полный текст новости
    private String fullText;

    // Ссылка на адрес фотографии
    private String photoLink;

    // Указатель публичности новости. Новость отображается для всех посетителей если true
    //private boolean hidden;

    // Указатель закрепления новости. Новость закреплена если true
    private boolean pinned;

    // Указатель видимости новости. Новость отображается если true
    private boolean visible;
}
