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

    @ApiOperation(value = "Выводит список релевантных новостей")
    @GetMapping(produces = PRODUCE_TYPE)
    public ResponseEntity<List<NewsDto>> readRelevantNews(Authentication authentication) {
        List<NewsDto> newsDtoList = newsService.getRelevantDto(authentication);
        return newsDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(newsDtoList, HttpStatus.OK);
    }
}
