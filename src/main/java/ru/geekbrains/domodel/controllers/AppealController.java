package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;
import ru.geekbrains.domodel.services.api.AppealService;

import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.mappers.ResponseMapper.getDtoResponse;
import static ru.geekbrains.domodel.mappers.ResponseMapper.getListAppealDtoResponse;

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

    @ApiOperation(value = "Выводит список обращений текущего пользователя")
    @GetMapping()
    public ResponseEntity<List<AppealDto>> readAllByUser(Authentication authentication) {
        return getListAppealDtoResponse(appealsService.getAllDtoByUser(authentication));
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Выводит список всех обращений")
    @GetMapping("/all")
    public ResponseEntity<List<AppealDto>> readAll(Authentication authentication) {
        return getListAppealDtoResponse(appealsService.getAllDto(authentication));
    }

    @ApiOperation(value = "Выводит обращение по его индексу")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AppealDto> readAppealById(@PathVariable(name = "id") Long id,
                                                    Authentication authentication) {
        return getDtoResponse(appealsService.getDtoById(id, authentication));
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Изменяет обращение по его индексу")
    @PostMapping(value = "/{id}", consumes = CONSUME_TYPE)
    public ResponseEntity<AppealDto> updateAppealById(@RequestBody AppealDto appealDto,
                                                      Authentication authentication) {
        return getDtoResponse(appealsService.update(appealDto, authentication));
    }

    @ApiOperation(value = "Создает обращение")
    @PostMapping(consumes = CONSUME_TYPE)
    public ResponseEntity<AppealDto> createAppeal(@RequestBody AppealRequest appealRequest,
                                                  Authentication authentication) {
        return getDtoResponse(appealsService.save(appealRequest, authentication));
    }
}
