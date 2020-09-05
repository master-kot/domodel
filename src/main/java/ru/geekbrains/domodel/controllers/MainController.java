package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.NewUserDataDto;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.UserDto;
import ru.geekbrains.domodel.security.jwt.JwtTokenFilter;
import ru.geekbrains.domodel.security.jwt.JwtTokenProvider;
import ru.geekbrains.domodel.services.api.NewsService;
import ru.geekbrains.domodel.services.api.UserService;

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
    private final UserService userService;
    private final NewsService newsService;
    private final AuthenticationManager authenticationManager;

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

    /**
     * Перехват запроса списка новостей для главной страницы
     */
    @GetMapping(produces = PRODUCE_TYPE)
    public List<NewsDto> getRelevantNews(Authentication authentication) {
        return newsService.getAllRelevantNews(authentication);
    }

    @PostMapping(consumes = PRODUCE_TYPE)
    public ResponseEntity<UserDto> createUser(@RequestBody NewUserDataDto userData) {
        UserDto userDto = userService.createUser(userData);
        if(userDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
