package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.NewsDto;
import ru.geekbrains.domodel.dto.NewsRequest;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.dto.VoteRequest;
import ru.geekbrains.domodel.services.api.VoteService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    @GetMapping("/{id}")
    public ResponseEntity<VoteDto> getVotesById(@PathVariable Long id,
                                                Authentication authentication) {
        return new ResponseEntity<>(voteService.getVotesDtoById(id, authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Выдает список проголосовавших для админа")
    @GetMapping("/{id}/detail")
    public ResponseEntity<TreeMap<String,String>> getVotesDetailById(@PathVariable Long id,
                                                 Authentication authentication) {
        return getMapResponseEntity(voteService.getVotesDtoDetailById(id, authentication));
    }

    @ApiOperation(value = "Выдает список голосований")
    @GetMapping("")
    public ResponseEntity<List<VoteDto>> getAllVotes(Authentication authentication) {
        return getListResponseEntity(voteService.getAllVotesDto(authentication));
    }

    @ApiOperation(value = "Создает голосование")
    @PostMapping
    public ResponseEntity<VoteDto> createVotes(@RequestBody VoteRequest voteRequest,
                                              Authentication authentication) {
        return getVotesDtoResponseEntity(voteService.save(voteRequest, authentication));
    }

    @ApiOperation(value = "Проголосовать")
    @PostMapping("/{id}/{choice}")
    public ResponseEntity<VoteDto> updateVotesById(@PathVariable Long id,
                                                   @PathVariable String choice,
                                                       Authentication authentication) {
        return getVotesDtoResponseEntity(voteService.updateVoteDtoById(id, authentication, choice));
    }


    /**
     * Формирует необходимый ответ в зависимости от содержания списка voteDtoList
     */
    private ResponseEntity<TreeMap<String,String>> getMapResponseEntity(TreeMap<String,String> map) {
        return map.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(map, HttpStatus.OK);
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
    private ResponseEntity<VoteDto> getVotesDtoResponseEntity(VoteDto voteDto) {
        return voteDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(voteDto, HttpStatus.OK);
    }
}
