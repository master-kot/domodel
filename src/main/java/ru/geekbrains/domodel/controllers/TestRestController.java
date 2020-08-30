package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.entities.Meter;
import ru.geekbrains.domodel.entities.News;
import ru.geekbrains.domodel.services.api.MeterService;
import ru.geekbrains.domodel.services.api.NewsService;

import java.util.List;

/**
 * Тестовый REST контроллер
 */
@CrossOrigin
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class TestRestController {

    // Необходимые сервисы
    private final MeterService meterService;
    private final NewsService newsService;

    // Тип объекта
    private final String PRODUCE_TYPE = "application/json";

    /**
     * Перехват запроса списка новостей
     */
    @GetMapping(path = "/news", produces = PRODUCE_TYPE)
    public List<News> getAllNews() {
        return newsService.readAllNews();
    }

    /**
     * Перехват запроса списка новостей
     */
    @GetMapping(path = "/meters", produces = PRODUCE_TYPE)
    public List<Meter> getAllMeters() {
        return meterService.getAllMeters();
    }

    /**
     * Перехват запроса создания новости
     */
    @PostMapping(path = "/news", consumes = PRODUCE_TYPE, produces = PRODUCE_TYPE)
    public News createNews(@RequestBody News news) {
        return newsService.saveNews(news);
    }

    /**
     * Перехват запроса удаления новости
     */
    //TODO исправить с удаления на изменение
//    @DeleteMapping(path = "/news/{id}")
//    public void deleteNews(@PathVariable("id") Long id) {
//        newsService.updateNewsById(id);
//    }
}
