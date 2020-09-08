package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.services.api.VoteService;

import java.util.List;

/**
 * Контроллер голосований
 */
@CrossOrigin
@RestController
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
        return getNewsDtoResponseEntity(voteService.getDtoById(id, authentication));
    }

    @ApiOperation(value = "Выдает список голосований")
    @GetMapping("/archive/{id}")
    public ResponseEntity<List<VoteDto>> readAllVotes(Authentication authentication) {
        return null; //getListResponseEntity(voteService.getAllDto(authentication));
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания списка voteDtoList
     */
    private ResponseEntity<List<VoteDto>> getListResponseEntity(List<VoteDto> voteDtoList) {
        return voteDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(voteDtoList, HttpStatus.OK);
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания voteDto
     */
    private ResponseEntity<VoteDto> getNewsDtoResponseEntity(VoteDto voteDto) {
        return voteDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(voteDto, HttpStatus.OK);
    }
}
