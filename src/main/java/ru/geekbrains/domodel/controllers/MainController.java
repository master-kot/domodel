package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.NewUserDataDto;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.UserDto;
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

    // Список необходимых сервисов
    private final UserService userService;
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

    /**
     * Выводит список релевантных новостей
     */
    @ApiOperation(value = "Выводит список релевантных новостей")
    @GetMapping(produces = PRODUCE_TYPE)
    public List<NewsDto> readRelevantNews() {
        return newsService.getRelevantNews();
    }

    /**
     * Создает нового пользователя
     */
    @ApiOperation(value = "Создает нового пользователя")
    @PostMapping(consumes = PRODUCE_TYPE)
    public ResponseEntity<UserDto> createUser(@RequestBody NewUserDataDto userData) {
        UserDto userDto = userService.saveUser(userData);
        if(userDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
