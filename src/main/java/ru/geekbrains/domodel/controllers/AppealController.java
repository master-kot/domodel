package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;
import ru.geekbrains.domodel.services.api.AppealService;

import java.util.List;

/**
 * Контроллер обращений
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/appeals")
@RequiredArgsConstructor
public class AppealController {

    // Тип объекта
    private final String CONSUME_TYPE = "application/json";

    // Сервис обращений
    private final AppealService appealsService;

    @ApiOperation(value = "Выводит список обращений пользователя")
    @GetMapping()
    public ResponseEntity<List<AppealDto>> readAll(Authentication authentication) {
        List<AppealDto> appealDtoList = appealsService.getAllDtoByUser(authentication);
        return getResponseByAppealDtoList(appealDtoList);
    }

    @ApiOperation(value = "Выводит обращение по его индексу")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AppealDto> readAppealById(@PathVariable(name = "id") Long id,
                                                    Authentication authentication) {
        AppealDto appeal = appealsService.getDtoById(id, authentication);
        return getResponseByAppealDto(appeal);
    }

    @ApiOperation(value = "Изменяет обращение по его индексу")
    @PostMapping(value = "/{id}", consumes = CONSUME_TYPE)
    public ResponseEntity<AppealDto> updateAppealById(@RequestBody AppealDto appealDto,
                                                      Authentication authentication) {
        AppealDto updatedAppeal = appealsService.update(appealDto, authentication);
        return getResponseByAppealDto(updatedAppeal);
    }


    @ApiOperation(value = "Создает обращение")
    @PostMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<AppealDto> createAppeal(@RequestBody AppealRequest appealRequest,
                                                  Authentication authentication) {
        AppealDto appeal = appealsService.save(appealRequest, authentication);
        return getResponseByAppealDto(appeal);
    }

    /**
     * Создает ответ по полученному Dto объекту
     */
    private ResponseEntity<AppealDto> getResponseByAppealDto(AppealDto appealDto) {
        return appealDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    /**
     * Создает ответ по полученному списку Dto объектов
     */
    private ResponseEntity<List<AppealDto>> getResponseByAppealDtoList(List<AppealDto> appealDtoList) {
        return appealDtoList.size() != 0 ?
             new ResponseEntity<>(appealDtoList, HttpStatus.OK) :
             new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
