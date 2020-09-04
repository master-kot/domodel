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

    // Список необходимых сервисов
    private final NewsService newsService;

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * NewsDto getById(Long id) найти объект по параметру
     * Collection<NewsDto> getAll() найти все объекты
     * Collection<NewsDto> getAllByUser(UserDto userDto) найти все объекты по параметру
     * News update(NewsDto newsDto) изменить объект
     * News save(NewsDto newsDto) сохранить объект
     * Collection<NewsDto> saveAll(Collection<NewsDto> newsDtoCollection) сохранить список объектов
     * void delete(NewsDto newsDto) удалить конкретный объект
     * Long deleteById(Long id) удалить объект по параметру
     * void deleteAll(Collection<NewsDto> newsDtoCollection) удалить список объектов
     */

    // TODO предлагаю в контроллере аналогичное именование

}
