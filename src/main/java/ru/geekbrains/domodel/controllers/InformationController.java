package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.domodel.dto.DocumentDto;
import ru.geekbrains.domodel.dto.InformationDto;
import ru.geekbrains.domodel.dto.InformationRequest;
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

    // Необходимые сервисы
    private final InformationService informationService;
    private final DocumentService documentService;

    @ApiOperation(value = "Выводит список информационных блоков раздела контакты")
    @GetMapping("/contacts")
    public ResponseEntity<List<InformationDto>> readContacts() {
        return getListInformationDtoResponseEntity(informationService.getAllDto());
    }

    @ApiOperation(value = "Создает информационный блок раздела контакты")
    @PostMapping("/contacts")
    public ResponseEntity<InformationDto> createInformation(@RequestBody InformationRequest informationRequest,
                                                            Authentication authentication) {
        InformationDto informationDto = informationService.save(informationRequest, authentication);
        return getInformationDtoResponseEntity(informationDto);
    }

    @ApiOperation(value = "Выводит список документов")
    @GetMapping("/documents")
    public ResponseEntity<List<DocumentDto>> readDocuments() {
        return getListDocumentDtoResponseEntity(documentService.getAllDto());
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания списка documentDtoList
     */
    private ResponseEntity<List<DocumentDto>> getListDocumentDtoResponseEntity(List<DocumentDto> documentDtoList) {
        return documentDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(documentDtoList, HttpStatus.OK);
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания списка informationDtoList
     */
    private ResponseEntity<List<InformationDto>> getListInformationDtoResponseEntity(List<InformationDto> informationDtoList) {
        return informationDtoList.size() == 0 ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(informationDtoList, HttpStatus.OK);
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания objectDto
     */
    private ResponseEntity<DocumentDto> getDocumentDtoResponseEntity(DocumentDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }

    /**
     * Формирует необходимый ответ в зависимости от содержания objectDto
     */
    private ResponseEntity<InformationDto> getInformationDtoResponseEntity(InformationDto objectDto) {
        return objectDto == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(objectDto, HttpStatus.OK);
    }
}
