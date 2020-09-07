package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequestDto;
import ru.geekbrains.domodel.services.api.NewsService;

/**
 * Контроллер новостей
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    // Тип объекта
    private final String CONSUME_TYPE = "application/json";

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

    /**
     * Создает новость
     */
    @ApiOperation(value = "Создает новость")
    @PostMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsRequestDto newsRequestDto,
                                              Authentication authentication) {
        NewsDto newsDto = newsService.save(newsRequestDto, authentication);
        if (newsDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsDto, HttpStatus.OK);
    }
}
