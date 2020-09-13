package ru.geekbrains.domodel.dto;
import ru.geekbrains.domodel.entities.Document;
import ru.geekbrains.domodel.entities.PhotoLink;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoteRequest {

    // Тема Опроса (до 50 знаков)
    private String title;

    // Предмет опроса (С учетом перехода текст до 500 знаков)
    private String text;

    // Дата начала опроса (не должна быть меньше текущей даты)
    private LocalDate startDate;

    // Дата завершения опроса (не должна быть меньше текущей даты и даты начала опроса)
    private LocalDate endDate;

    // Список ссылок на фото (2 шт.), загружается при необходимости
    private List<PhotoLink> photoLinks = new ArrayList<>();

    // Список ссылок на документы (2 шт.), загружается  при необходимости
    private List<Document> documents = new ArrayList<>();
}
