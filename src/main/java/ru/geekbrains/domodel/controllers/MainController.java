package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.List;

/**
 * Главный контроллер web-приложения
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MainController {

    // Тип объекта
    private final String PRODUCE_TYPE = "application/json";

    // Необходимые сервисы
    private final NewsService newsService;

    /**
     * Перехват запроса списка новостей для главной страницы
     */
    @GetMapping(produces = PRODUCE_TYPE)
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }
}
