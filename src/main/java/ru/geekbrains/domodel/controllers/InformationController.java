package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;
import ru.geekbrains.domodel.services.api.DocumentService;
import ru.geekbrains.domodel.services.api.InformationService;

import java.util.List;

import static ru.geekbrains.domodel.entities.constants.Roles.ROLE_ADMIN;
import static ru.geekbrains.domodel.mappers.ResponseMapper.*;

/**
 * Контроллер информации
 */
@ApiOperation(value = "Контроллер информации")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/information")
@RequiredArgsConstructor
public class InformationController {

    // Необходимые сервисы
    private final InformationService informationService;
    private final DocumentService documentService;

    @ApiOperation(value = "Выводит список информационных блоков раздела контакты")
    @GetMapping("/contacts")
    public ResponseEntity<List<InformationDto>> readContacts() {
        return getListInformationDtoResponse(informationService.getAllDto());
    }

    @Secured(value = {ROLE_ADMIN})
    @ApiOperation(value = "Создает информационный блок раздела контакты. Только для Администратора")
    @PostMapping("/contacts")
    public ResponseEntity<InformationDto> createInformation(@RequestBody InformationRequest informationRequest,
                                                            Authentication authentication) {
        return getDtoResponse(informationService.save(informationRequest, authentication));
    }

    @ApiOperation(value = "Выводит список документов")
    @GetMapping("/documents")
    public ResponseEntity<List<DocumentDto>> readDocuments() {
        return getListDocumentDtoResponse(documentService.getAllDto());
    }
}
