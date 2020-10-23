package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.VoteDto;
import ru.geekbrains.domodel.dto.VoteRequest;
import ru.geekbrains.domodel.services.api.VoteService;

import java.util.List;
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

    @ApiOperation(value = "Выдает список голосований")
    @GetMapping("")
    public ResponseEntity<List<VoteDto>> readAllVotesByUser(Authentication authentication) {
        return new ResponseEntity<>(voteService.getAllDto(authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Выдает голосование по его номеру")
    @GetMapping("/{id}")
    public ResponseEntity<VoteDto> getVoteById(@PathVariable Long id,
                                              Authentication authentication) {
        return new ResponseEntity<>(voteService.getDtoById(id, authentication), HttpStatus.OK);
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Выдает список проголосовавших для админа")
    @GetMapping("/{id}/detail")
    public ResponseEntity<TreeMap<String,String>> readVotesDetailById(@PathVariable Long id,
                                                                     Authentication authentication) {
        return new ResponseEntity<>(voteService.getVotesDtoDetailById(id, authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Создает голосование")
    @PostMapping
    public ResponseEntity<VoteDto> createVote(@RequestBody VoteRequest voteRequest,
                                               Authentication authentication) {
        return new ResponseEntity<>(voteService.save(voteRequest, authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Проголосовать")
    @PostMapping("/{id}/{choice}")
    public ResponseEntity<VoteDto> updateVoteById(@PathVariable Long id,
                                                  @PathVariable String choice,
                                                  Authentication authentication) {
        return new ResponseEntity<>(voteService.update(id, authentication, choice), HttpStatus.OK);
    }
}
