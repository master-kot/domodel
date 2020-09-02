package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.services.api.NewsService;

/**
 * Контроллер новостей
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    // Сервис новостей
    private final NewsService newsService;

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * NewsDto getNewsById(Long id) найти объект по параметру
     * List<NewsDto> getAllNews() найти все объекты
     * List<NewsDto> getAllNewsByUser(UserDto user) найти все объекты по параметру
     * News updateNews(NewsDto news) изменить объект
     * News saveNews(NewsDto newsDto) сохранить объект
     * List<NewsDto> saveAllNews(List<NewsDto> newsDtoList) сохранить список объектов
     * void deleteNews(NewsDto newsDto) удалить конкретный объект
     * Long deleteNewsById(Long id) удалить объект по параметру
     * void deleteAllNews(List<NewsDto> newsDtoList) удалить список объектов
     */

    // TODO предлагаю в контроллере аналогичное именование

}
