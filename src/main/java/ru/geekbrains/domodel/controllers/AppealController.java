package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.AppealDto;
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
    @GetMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<List<AppealDto>> readAll(Authentication authentication) {
        List<AppealDto> appealDtoList = appealsService.getAll(authentication);
        return createResponseByAppealList(appealDtoList);
    }

    @ApiOperation(value = "Выводит обращение по его индексу")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AppealDto> readAppealById(@PathVariable(name = "id") Long id,
                                                    Authentication authentication) {
        AppealDto appeal = appealsService.getById(id, authentication);
        return createResponseByAppeal(appeal);
    }

    @ApiOperation(value = "Изменяет обращение по его индексу")
    @PostMapping(value = "/{id}")
    public ResponseEntity<AppealDto> updateAppealById(@RequestBody AppealDto appealDto,
                                                      Authentication authentication) {
        AppealDto appeal = appealsService.update(appealDto, authentication);
        return createResponseByAppeal(appeal);
    }

    /**
     * Создает новость
     */
    @ApiOperation(value = "Создает новость")
    @PostMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<AppealDto> createAppeal(@RequestBody AppealDto appealDto,
                                                  Authentication authentication) {
        AppealDto appeal = appealsService.save(appealDto, authentication);
        return createResponseByAppeal(appeal);
    }

    /**
     * Создает ответ по полученному Dto объекту
     */
    private ResponseEntity<AppealDto> createResponseByAppeal(AppealDto appealDto) {
        if (appealDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    /**
     * Создает ответ по полученному списку Dto объектов
     */
    private ResponseEntity<List<AppealDto>> createResponseByAppealList(List<AppealDto> appealDtoList) {
        if (appealDtoList.size() != 0) {
            return new ResponseEntity<>(appealDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
