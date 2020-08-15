package ru.geekbrains.domodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.domodel.services.api.PhotosService;

import java.security.Principal;

/**
 * Контроллер фотогалереи
 */
@Controller
@RequestMapping("/photos")
public class PhotosController {

    // Сервис фотогалереи
    private final PhotosService photosService;

    @Autowired
    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }

    /**
     * Перехват запроса списка фотографий
     */
    @GetMapping("")
    public String getPhotosPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "/pages/photos";
    }
}
