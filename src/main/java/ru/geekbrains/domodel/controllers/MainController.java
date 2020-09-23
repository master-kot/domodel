package ru.geekbrains.domodel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Главный контроллер фронтенд части web-приложения
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    /**
     * Перехват запроса главной страницы
     */
    @GetMapping("")
    public String getHomePage() {
        return "index";
    }
}
