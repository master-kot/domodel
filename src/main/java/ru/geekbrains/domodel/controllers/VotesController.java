package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.services.api.VoteService;

import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Контроллер голосований
 */
@ApiOperation("Контроллер голосований. Доступ только для зарегистрированных пользователей и Администратора")
@CrossOrigin
@RestController
@Secured(value = {ROLE_USER, ROLE_ADMIN})
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VotesController {

    // Тип данных
    private final String DATA_TYPE = "application/json";

    // Сервис голосований
    private final VoteService voteService;

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * News getById(Long id) найти объект по параметру
     * NewsDto getDtoById(Long id) найти Dto объект по параметру
     * Collection<News> getAll() найти все объекты
     * Collection<NewsDto> getAllDto() найти все Dto объекты
     * Collection<NewsDto> getAllDtoByUser(UserDto userDto) найти все Dto объекты по параметру
     * NewsDto update(NewsDto newsDto) изменить объект
     * NewsDto save(NewsDto newsDto) сохранить объект
     * Collection<NewsDto> saveAllDto(Collection<NewsDto> newsDtoCollection) сохранить список объектов
     * void delete(NewsDto newsDto) удалить конкретный объект
     * void deleteById(Long id) удалить объект по параметру
     * void deleteAll(Collection<NewsDto> newsDtoCollection) удалить список объектов
     */

    @ApiOperation(value = "Выдает голосование по его номеру")
    @GetMapping(value = "/{id}", produces = DATA_TYPE)
    public ResponseEntity<VoteDto> readNewsById(@PathVariable Long id,
                                                Authentication authentication) {
        return new ResponseEntity<>(voteService.getDtoById(id, authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Выдает список голосований для данного пользователя")
    @GetMapping(value = "/archive/{id}", produces = DATA_TYPE)
    public ResponseEntity<List<VoteDto>> readAllVotes(Authentication authentication) {
        return new ResponseEntity<>(voteService.getAllDto(authentication), HttpStatus.OK);
    }
}
