package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.services.api.DocumentService;
import ru.geekbrains.domodel.services.api.InformationService;

import java.util.List;

/**
 * Контроллер информации
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/information")
@RequiredArgsConstructor
public class InformationController {

    // Сервис информации
    private final InformationService informationService;
    private final DocumentService documentService;

    @ApiOperation(value = "Выводит список контактов")
    @GetMapping("/information")
    public List<InformationDto> readContacts() {
        return informationService.getAll();
    }

    @ApiOperation(value = "Выводит список документов")
    @GetMapping("/documents")
    public List<DocumentDto> readDocuments() {
        return documentService.getAll();
    }
}
