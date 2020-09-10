package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.dto.RequisitesDto;
import ru.geekbrains.domodel.services.api.RequisitesService;
import ru.geekbrains.domodel.services.api.UserService;

/**
 * Контроллер модуля управления сайтом
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
public class ManagementController {

    // Необходимые сервисы
    private final UserService userService;
    private final RequisitesService requisitesService;

    @ApiOperation(value = "Выводит текущие реквизиты")
    @GetMapping("/requisites")
    public RequisitesDto readRequisitesPage() {
        return requisitesService.getRelevantDto();
    }
}
