package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.dto.NewsDto;
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

    @ApiOperation(value = "Выводит список релевантных новостей")
    @GetMapping(produces = PRODUCE_TYPE)
    public ResponseEntity<List<NewsDto>> readRelevantNews(Authentication authentication) {
        List<NewsDto> newsDtoList = newsService.getRelevantNews(authentication);
        if (newsDtoList.size() != 0) {
            return new ResponseEntity<>(newsDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
