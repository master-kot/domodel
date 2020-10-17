package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.AppealDto;
import ru.geekbrains.domodel.dto.AppealRequest;
import ru.geekbrains.domodel.services.api.AppealService;

import javax.validation.Valid;
import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_USER;

/**
 * Контроллер обращений
 */
@Api(value = "Контроллер обращений")
@CrossOrigin
@Secured(value = {ROLE_USER, ROLE_ADMIN})
@RestController
@RequestMapping("/api/v1/appeals")
@RequiredArgsConstructor
public class AppealController {

    // Тип объекта
    private final String DATA_TYPE = "application/json";

    // Сервис обращений
    private final AppealService appealsService;

    @ApiOperation(value = "Выводит список обращений текущего пользователя")
    @GetMapping(value = "", produces = DATA_TYPE)
    public ResponseEntity<List<AppealDto>> readAllByUser(Authentication authentication) {
        return new ResponseEntity<>(appealsService.getAllDtoByUser(authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Создает обращение")
    @PostMapping(value = "", produces = DATA_TYPE)
    public ResponseEntity<AppealDto> createAppeal(@Valid @RequestBody AppealRequest appealRequest,
                                                  Authentication authentication) {
        return new ResponseEntity<>(appealsService.save(appealRequest, authentication), HttpStatus.OK);
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Выводит список всех обращений. Только для Администратора")
    @GetMapping(value = "/all", produces = DATA_TYPE)
    public ResponseEntity<List<AppealDto>> readAll(Authentication authentication) {
        return new ResponseEntity<>(appealsService.getAllDto(authentication), HttpStatus.OK);
    }

    @ApiOperation(value = "Выводит обращение по его индексу")
    @GetMapping(value = "/{id}", produces = DATA_TYPE)
    public ResponseEntity<AppealDto> readAppealById(@PathVariable(name = "id") Long id,
                                                    Authentication authentication) {
        return new ResponseEntity<>(appealsService.getDtoById(id, authentication), HttpStatus.OK);
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Изменяет обращение по его индексу. Только для Администратора")
    @PostMapping(value = "/{id}", produces = DATA_TYPE)
    public ResponseEntity<AppealDto> updateAppealById(@Valid @RequestBody AppealDto appealDto,
                                                      Authentication authentication) {
        return new ResponseEntity<>(appealsService.update(appealDto, authentication), HttpStatus.OK);
    }
}
