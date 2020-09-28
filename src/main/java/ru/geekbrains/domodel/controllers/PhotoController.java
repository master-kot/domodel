package ru.geekbrains.domodel.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.services.api.PhotoService;

/**
 * Контроллер фотогалереи
 */
@ApiOperation(value = "Контроллер фотогалереи")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/photos")
@RequiredArgsConstructor
public class PhotoController {

    // Сервис фотогалереи
    private final PhotoService photoService;
}
