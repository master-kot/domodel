package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;
import ru.geekbrains.domodel.services.api.DocumentService;
import ru.geekbrains.domodel.services.api.InformationService;

import javax.validation.Valid;
import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;

/**
 * Контроллер информации
 */
@Api(value = "Контроллер информации")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/information")
@RequiredArgsConstructor
public class InformationController {

    // Тип данных
    private final String DATA_TYPE = "application/json";

    // Необходимые сервисы
    private final InformationService informationService;
    private final DocumentService documentService;

    @ApiOperation(value = "Выводит список информационных блоков раздела контакты")
    @GetMapping(value = "/contacts", produces = DATA_TYPE)
    public ResponseEntity<List<InformationDto>> readContacts() {
        return new ResponseEntity<>(informationService.getAllDto(), HttpStatus.OK);
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Выводит информационный блок раздела контакты по его номеру")
    @GetMapping(value = "/contacts/{id}", produces = DATA_TYPE)
    public ResponseEntity<InformationDto> readContactsById(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(informationService.getDtoById(id), HttpStatus.OK);
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Изменяет информационный блок раздела контакты. Только для Администратора")
    @PostMapping(value = "/contacts/{id}", produces = DATA_TYPE)
    public ResponseEntity<InformationDto> updateInformation(@Valid @RequestBody InformationDto informationRequest) {
        return new ResponseEntity<>(informationService.update(informationRequest), HttpStatus.OK);
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Создает информационный блок раздела контакты. Только для Администратора")
    @PostMapping(value = "/contacts", produces = DATA_TYPE)
    public ResponseEntity<InformationDto> createInformation(@Valid @RequestBody InformationRequest informationRequest) {
        return new ResponseEntity<>(informationService.save(informationRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Выводит список всех документов")
    @GetMapping(value = "/documents", produces = DATA_TYPE)
    public ResponseEntity<List<DocumentDto>> readDocuments() {
        return new ResponseEntity<>(documentService.getAllDto(), HttpStatus.OK);
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Создает документ")
    @PostMapping(value = "/documents", produces = DATA_TYPE)
    public ResponseEntity<DocumentDto> createDocuments(@Valid @RequestBody DocumentDto documentRequest) {
        return new ResponseEntity<>(documentService.save(documentRequest), HttpStatus.OK);
    }
}
