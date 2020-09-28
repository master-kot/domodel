package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.services.api.VoteService;

import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;
import static ru.geekbrains.domodel.mappers.ResponseMapper.getDtoResponse;
import static ru.geekbrains.domodel.mappers.ResponseMapper.getListVoteDtoResponse;

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
    @GetMapping("/{id}")
    public ResponseEntity<VoteDto> readNewsById(@PathVariable Long id,
                                                Authentication authentication) {
        return getDtoResponse(voteService.getDtoById(id, authentication));
    }

    @ApiOperation(value = "Выдает список голосований для данного пользователя")
    @GetMapping("/archive/{id}")
    public ResponseEntity<List<VoteDto>> readAllVotes(Authentication authentication) {
        return getListVoteDtoResponse(voteService.getAllDto(authentication));
    }
}
