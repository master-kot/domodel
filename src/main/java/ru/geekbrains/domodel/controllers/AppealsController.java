package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.services.api.AppealsService;

/**
 * Контроллер обращений
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/appeals")
@RequiredArgsConstructor
public class AppealsController {

    // Сервис обращений
    private final AppealsService appealsService;
}
