package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.domodel.services.api.PhotosService;

/**
 * Контроллер фотогалереи
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/photos")
@RequiredArgsConstructor
public class PhotosController {

    // Сервис фотогалереи
    private final PhotosService photosService;
}
